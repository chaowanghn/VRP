package algorithms.construction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Customers;
import util.Nodes;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

import model.Solution;
import model.TTRP;
import model.fleet.*;
import model.routes.*;

import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;
import model.routes.Route;

/*
 * Implement the T-Cluster construction heuristics as described in [3]
 * 
 * T-Cluster can be considered as a 
 * cluster-based sequential insertion procedure, 
 * where routes are constructed one by one up to full vehicle utilization.
 * 
 * A new route is initialized with the unrouted customers farthest away from the depot 
 * and the
 * unused vehicle having maximum total capacity. 
 * Thereby, complete vehicles are always preferred over pure trucks. 
 * 
 * In case of a complete vehicle, if the seed customer is a VC customer, then it is inserted into
 * the main-tour. On the other hand it is inserted into a new sub-tour to the depot, if it is a TC customer.
 * 
 * The next customer for insertion into the routes is then selected from the unrouted customers minimizing
 * 		e(k) = c(ku) + c(kf) - πc(0k)
 * 		where
 * 		k specifies the customer under consideration
 * 		f the nearest customer already routed in this route, and 
 * 		0 the depot. 
 * 
 * For customer k the formulae can be explained as follows:
 * 
 * 		The first term c(ku) measures the distance to the seed customer u of this route. This distance should be 
 * 		kept at minimum to ensure compact routes.
 * 
 * 		The second term c(kf) ensures the customer is close
 * 		to the "border" of the current route. This proves helpful in instances with clustered customer, where a
 * 		route should preferably serve all customers of the group before spreading to another group of customers.
 * 
 * 		Finally, the third term πc(0k) can be considered as a diversification term. Varying the parameter π will result
 * 		in different selection strategies: the higher the value of π, the higher the tendency for a selection of a
 * 		customer located far away from the depot.
 * 
 * Having specified the next customer k, 
 * a feasible insertion into the route is evaluated via cheapest 
 * insertion. 
 * In case of a CVR, 
 * VC customers are forced into the main-tour and 
 * TC customers into a new or existing sub-tour. 
 * 
 * Every VC customer on the main-tour and the depot can thereby be selected as a trailer-parking place for a new sub-tour. 
 * 
 * If the insertion of customer k would result in a violation of total vehicle capacity or maximum route length, 
 * a new route is initialized and the customer k remains unrouted. 
 * 
 * Only the last route (and its sub-tours in case of a CVR) are allowed to become infeasible if no more vehicle are available.
 * 		 
 */

public class TCluster implements ConstructionHeuristic {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static double DEFAULT_PI = 0.5;
	private List<Customer> customers;
	private Fleet fleet;
	private Depot depot;
	private Set<Route<?,?,?>> routes;
	private Solution solution;

	public Solution apply(TTRP ttrp) {
		checkState(Customers.getSatisfied(ttrp.getCustomers()).isEmpty(),"initially none of the customers is satisfied");
		this.customers = new ArrayList<Customer>(ttrp.getCustomers());
		checkState(ttrp.getFleet().hasAvailabeTrailers() && ttrp.getFleet().hasAvailabeTrucks(),"there have to be available vehicle in the depot");
		this.fleet = ttrp.getFleet();
		this.depot = ttrp.getDepot();
		this.solution = new Solution();
		
		// T-Cluster can be considered as a cluster-based sequential insertion procedure, where routes are constructed one by one up to full vehicle utilization.
		Set<Route<?,?,?>> routes = new HashSet<Route<?,?,?>>(); 
		while(Iterables.any(customers, Customers.notSatisfied())) {
			logger.info("=========================================");
			logger.info("main loop started. Number of unsatisfied customers: " + Customers.getNotSatisfied(customers).size());
			Route<?,?,?> routeUnderConstruction = this.initializeNewRoute(); // the exact type of route is not known yet. It's determined at runtime!
			checkNotNull(routeUnderConstruction);
			MovingObject vehicle = routeUnderConstruction.getVehicle(); 
			logger.info("root initialized with type: " + routeUnderConstruction.getClass() + " and vehicle type" + vehicle.getClass());
			checkNotNull(vehicle);
			Customer seedCustomer = Nodes.farthest(Customers.getNotSatisfied(customers), depot);
			logger.info("seed customer selected " + seedCustomer.getClass());
			checkNotNull(seedCustomer);
			
			if (vehicle instanceof CompleteVehicle) {
				logger.info("complete vehicle route construction started");
				// In case of a complete vehicle, if the seed customer is a VC customer, then it is inserted into the main-tour.  On the other hand it is inserted into a new sub-tour to the depot, if it is a TC customer.
				if(seedCustomer instanceof VehicleCustomer) {
					logger.info("adding seed customer to CVR");
					((CompleteVehicleRoute) routeUnderConstruction).addToMainTour((VehicleCustomer) seedCustomer);
				}
				else {
					checkState(seedCustomer instanceof TruckCustomer);
					SubTour st = new SubTour(depot, ((CompleteVehicle) vehicle).getTruck());
					st.addCustomer(seedCustomer);
					logger.info("seed customer added to SubTour: " + st.toString());
				}
				
				List<Customer> candidateCustomers = this.candidateCustomersAfterTheSeed();
				logger.info("adding the rest of the customers started. Candidates list size: " + candidateCustomers.size());
				addRestOfTheCustomers(routeUnderConstruction, candidateCustomers);
			}
			
			else {
				checkArgument(vehicle instanceof Truck);
				//if a CompleteVehice can't be used (probably because there are no trailers available) a new pure truck route is constructed
				logger.info("Pure Truck Route construction started");
				routeUnderConstruction = new PureTruckRoute(depot,(Truck) vehicle);
			}
			
		}
		
		return solution;
	}
	
	public void addRestOfTheCustomers(Route<?,?,?> routeUnderConstruction, List<Customer> candidateCustomers){
		for(Customer candidate : candidateCustomers) {
			if(routeUnderConstruction.feasibleInsertion(candidate)){
				if(routeUnderConstruction instanceof CompleteVehicleRoute){
					if(candidate instanceof VehicleCustomer) {
						((CompleteVehicleRoute) routeUnderConstruction).addToMainTour((VehicleCustomer) candidate);
						logger.info("NEXT CUSTOMER add to main tour");
					}
					else {
						if(! ((CompleteVehicleRoute) routeUnderConstruction).hasSubTours()) {
							SubTour st = new SubTour(depot);
							st.addCustomer((TruckCustomer) candidate);
							logger.info("NEXT CUSTOMER add to a new SubTour");
						}
						else {
							Iterables.getFirst(((CompleteVehicleRoute) routeUnderConstruction).getSubTours(), null).addCustomer(candidate);
							logger.info("NEXT CUSTOMER add to an existing SubTourr");
						}
					}
				}
			}
		}
	}

	private List<Customer> candidateCustomersAfterTheSeed() {
		return new ArrayList<Customer>(Customers.getNotSatisfied(this.customers));
	}

	private Route<?,?,?> initializeNewRoute() {
		/*
		 * A new route is initialized with the unrouted customer farthest away from the depot 
		 * and the
		 * unused vehicle having maximum total capacity. 
		 * Thereby, complete vehicles are always preferred over pure trucks. 
		 * */
		Customer u = Nodes.farthest(Customers.getNotSatisfied(customers), depot); // the seed customer
		MovingObject vehicle = fleet.getUnusedVehicleWithMaxCapacity();
		checkNotNull(vehicle);
		Route<?,?,?> route=null;
		if (vehicle instanceof CompleteVehicle) {
			route = new CompleteVehicleRoute(depot,(CompleteVehicle) vehicle);
		}
		else {
			route = new PureTruckRoute(depot, (Truck) vehicle);
		}
		return route;	
	}
	
	
	private class NextCustomerComparator implements Comparator<Customer> {
		private Depot depot;
		private double pi;
		private Customer u;
		private Route<?,?,?> r;
		public NextCustomerComparator(Depot depot, double pi, Customer u, Route<?,?,?> r) {
			this.depot = depot;
			this.pi = pi;
			this.u = u;
			this.r = r;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public int compare(Customer k1, Customer k2) {
			double e1 = e(k1, u, (Customer) Nodes.nearest(r.getCustomers(), k1), depot);
			double e2 = e(k2, u, (Customer) Nodes.nearest(r.getCustomers(), k2), depot);
			
			if (e1 < e2) {
				return -1;
			}
			
			else if (e1 > e2) {
				return 1;
			}
			
			else {
				return 0;
			}
		}
		
		private double e(Customer k, Customer u, Customer f, Depot d) {
			return k.distance(u) + k.distance(f) - pi * d.distance(k);
		}
	}
	
	

}
