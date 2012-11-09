package algorithms.construction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

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
	private static double DEFAULT_PI = 0.5;
	
	public static void createInitialTCluster(TTRP ttrp) {
		
	}

	public Solution apply(TTRP ttrp) {
		Solution solution = new Solution();
		Set<Customer> customers = ttrp.getCustomers();
		Fleet fleet = ttrp.getFleet();
		Depot depot = ttrp.getDepot();
		
		Set<Route> routes = new HashSet<Route>();
		
		/*
		 * T-Cluster can be considered as a 
		 * cluster-based sequential insertion procedure, 
		 * where routes are constructed one by one up to full vehicle utilization.
		 */
		routeConstruction:
		while(Iterables.any(customers, Customer.notSatisfied())) {
			checkArgument(fleet.hasAvailabeTrailers() || fleet.hasAvailabeTrucks());
			
			//============================ ROUTE INITIALIZATION ============================
			/*
			 * A new route is initialized with the unrouted customer farthest away from the depot 
			 * and the
			 * unused vehicle having maximum total capacity. 
			 * Thereby, complete vehicles are always preferred over pure trucks. 
			 * */
			
			Route routeUnderConstruction;
			MovingObject vehicle = getUnusedVehicleWithMaxCapacity(fleet);
			Customer u = Node.farthest(Collections2.filter(customers, Customer.notSatisfied()), depot); // the seed customer
			
			/*
			 * In case of a complete vehicle, if the seed customer is a VC customer, then it is inserted into
			 * the main-tour. On the other hand it is inserted into a new sub-tour to the depot, if it is a TC customer.
			 * */
			//============================ ADD SEED CUSTOMER ============================
			if (vehicle instanceof CompleteVehicle) {
				/*
				 * In case of a complete vehicle, if the seed customer is a VC customer, then it is inserted into
				 * the main-tour. On the other hand it is inserted into a new sub-tour to the depot, if it is a TC customer.
				 * */
				routeUnderConstruction = new CompleteVehicleRoute(depot, (CompleteVehicle) vehicle);
				if(u instanceof VehicleCustomer) {
					((CompleteVehicleRoute) routeUnderConstruction).addToMainTour((VehicleCustomer) u);
				}
				
				else {
					checkState(u instanceof TruckCustomer, "Since it's not a VehicleCustomer, it has to be a TruckCustomer");
					SubTour st = new SubTour(depot);
					st.addCustomer((TruckCustomer) u);
				}
				
				//============================ ADD THE REST OF THE CUSTOMERS ============================
				Customer k = Collections.min(customers, new NextCustomerComparator(depot, DEFAULT_PI, u, routeUnderConstruction));
				
			}
			
			else {
				//if a CompleteVehice can't be used (probably because there are no trailers available
			}
			
		}
		
		return solution;
	}

	private class NextCustomerComparator implements Comparator<Customer> {
		private Depot depot;
		private double pi;
		private Customer u;
		private Route r;
		public NextCustomerComparator(Depot depot, double pi, Customer u, Route r) {
			this.depot = depot;
			this.pi = pi;
			this.u = u;
			this.r = r;
		}
		
		@Override
		public int compare(Customer k1, Customer k2) {
			double e1 = e(k1, u, Node.nearest(r.getCustomers(), k1), depot);
			double e2 = e(k2, u, Node.nearest(r.getCustomers(), k2), depot);
			
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
	
	private MovingObject getUnusedVehicleWithMaxCapacity(Fleet fleet) {
		
		if(fleet.hasAvailabeTrailers() && fleet.hasAvailabeTrucks()) {
			Trailer trailer = fleet.getAvailableTrailerWithMaxCapacity();
			Truck truck = fleet.getAvailableTruckWithMaxCapacity();
			return new CompleteVehicle(truck, trailer, null);
		}
		
		else {
			checkState(fleet.hasAvailabeTrucks(), "this shouldn't happen");
			return fleet.getAvailableTruckWithMaxCapacity();
		}
	}

}
