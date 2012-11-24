package algorithms.construction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.*;

import cern.colt.list.IntArrayList;



import util.Customers;
import util.Nodes;
import model.Solution;
import model.TTRP;
import model.nodes.*;
import model.routes.*;
import model.fleet.*;


/*
 * Implements the construction heuristic based on a String representation of a TTRP solution
 * For implementation details see [9]
 * 
 * Description based on [9] 
 * 
 * 3.1 Solution representation and initial solution
 * 
 * Customers are classified as vehicle-serviced(VCs) and
 * truck-serviced customers (TCs). A solution is represented by a string
 * of number consisting of a permutation of n customers denoted by
 * the set {1,2,...,n} and Ndummy zeros (artificial depot or the root
 * of a sub-tour), followed by the service vehicle types of individual
 * VCs. 
 * 
 * The Ndummy zeros are used to separate routes or terminate a 
 * sub-tour. The parameter Ndummy is calculated by [Σ(d_i)/Qk], where
 * [.] denote the largest integer which is smaller than or equal to the
 * enclosed number. The ith non-zero number is the first n+Ndummy
 * positions denotes the ith customer to be serviced.
 * 
 * The service vehicle type of a VC is either 0 or 1. If the VC is
 * serviced by a complete vehicle, its service vehicle type is set to be
 * 0. Otherwise, it is service by a a truck alone, and its service vehicle
 * type is set to be 1. Note that a TC must be service by a a truck alone
 * and thus does not need to be represented in the solution. The service 
 * vehicle type of a VC determines the type of the vehicle used to service
 * the VC so that each solution representation corresponds to exactly
 * one TTRP solution.
 * 
 * The solution representation is further explained as follows. The
 * first number in the solution indicates the first customer to be serviced
 * in the first route. If the first customer on a route is to be serviced
 * by a single truck, the route is set to be a PTR. Other customers are 
 * added to the route one by one from left to right to represent the 
 * sequence in which they are serviced, provided that the capacity of
 * the vehicle in use is not violated. Note that, depending on the capacity of
 * the vehicle in use is not violated. Note that, depending on the type of
 * the service vehicle in use, the capacity of the vehicle may be (Qk+Qt)
 * if it is a complete vehicle on a PVR or on main tour of a CVR; 
 * or Qt if it is a truck servicing customers alone, on a PTR or on a sub-
 * tour of a CVR. If the next customer to be serviced in the solution 
 * representation is zero, the vehicle will either return to the root of a
 * sub-tour or the depot. If it is on a sub-tour of a CVR, it will return 
 * to the root of the sub-tour where the trailer was parked and the sub-
 * tour is terminated. Otherwise, it is on a PTR, on a PVR, or on a main 
 * tour of a CVR.In this case, the vehicle will return to the depot, and 
 * the route is terminated. 
 * 
 * Whenever a route is terminated and there are customers that
 * haven't been serviced, a new route will be generated starting with
 * the next customer in the solution representation.It can ver verified
 * that this solution representation always gives a TTRP solution with-
 * out violating the capacity constraint of vehicle in use.However, the
 * number of vehicle used may exceed the number of vehicles available 
 * using this solution representation. Therefore, a route combination
 * procedure that tries to reduce the number of vehicles used is needed 
 * after the routes are generated from the solution representation.
 * The route combination procedure simply checks if it is possible
 * to combine two existing routes without violating vehicle's capacity
 * constraint.If so, the routes are merged without any modification.
 * The procedure continues until the number of vehicle used is no more than
 * the number of available vehicle or there are no routes can be combined 
 * without violating the capacity constraint of the vehicle in use.
 * 
 * If the resulting solution still uses more vehicles than available,
 * for each extra truck or trailer used, a penalty P is added to the
 * objective function to make such solutions unattractive.
 * 
 * The initial solution is randomly generated. It is comprised of a
 * randomly ordered sequence of the customers and the dummy zeros,
 * and randomly set service vehicle types of VCs.
 * 
 */

public class StringSolutionRepresentation implements ConstructionHeuristic{
	private TTRP ttrp;
	private List<Node> permutation;
	private Map<VehicleCustomer, ServiceType> vcsServiceType = new HashMap<VehicleCustomer, ServiceType>();
	private Set<Route<Node,Customer,MovingObject>> routes = new HashSet<Route<Node,Customer,MovingObject>>();
	private List<List<Node>> potentialRoutes;
	
	
	@Override
	public Solution apply(TTRP ttrp) {
		checkNotNull(ttrp);
		this.ttrp = ttrp;
		this.setRandomServiceTypeForVCs(ttrp.getVehicleCustomers());
		int nDummy = calculateNdummy(ttrp);
		this.permutation = createRandomPermutation(createArtificialDepots(ttrp, nDummy), ttrp.getCustomers());
		this.potentialRoutes = Nodes.partition(this.permutation, this.depotIndices(permutation));
		
		
		return null;
		
	}
	
	
	public int[] depotIndices(List<Node> permutation) {
		IntArrayList depotIndices = new IntArrayList();
		for(int i=0; i<permutation.size(); i++) {
			if(permutation.get(i).equals(ttrp.getDepot())) {
				depotIndices.add(i);
			}
		}
		
		int[] indices = new int[depotIndices.size()];
		for(int i=0; i<indices.length; i++){
			indices[i] = depotIndices.get(i);
		}
		return indices;
		
	}

	public List<Node> createRandomPermutation(Collection<Depot> artificialDepots, Collection<? extends Customer> customers){
		List<Node> randomPermutation = new ArrayList<Node>();
		randomPermutation.addAll(artificialDepots);
		randomPermutation.addAll(customers);
		Collections.shuffle(randomPermutation);
		return randomPermutation;
	}
	
	public int calculateNdummy(TTRP ttrp){
		/*The parameter Ndummy is calculated by [Σ(d_i)/Qk], where
		 * [.] denote the largest integer which is smaller than or equal to the
		 * enclosed number. 
		 */ 
		return (int) Math.floor(Customers.totalDemand(ttrp.getCustomers()) / ttrp.getFleet().getTruckCapacity());
	}
	
	public List<Depot> createArtificialDepots(TTRP ttrp, int numberOfDepots){
		List<Depot> artificialDepots = new ArrayList<Depot>(numberOfDepots);
		for(int i=0; i<numberOfDepots; i++){
			artificialDepots.add(i, ttrp.getDepot());
		}
		return artificialDepots;
	}
	
	private void setRandomServiceTypeForVCs(Set<VehicleCustomer> vehicleCustomers){
		final Random random = new Random();
		for(VehicleCustomer vc : vehicleCustomers) {
			this.vcsServiceType.put(vc, ServiceType.randomServiceType(random));
		}
	}
	
	public List<Node> getPermutation(){
		return this.permutation;
	}
	
	public List<List<Node>> getPotentialRoutes(){
		return this.potentialRoutes;
	}

	private ServiceType getServiceType(Customer customer){
		if(customer instanceof TruckCustomer) {
			return ServiceType.TRUCK;
		}
		else {
			return this.vcsServiceType.get((VehicleCustomer) customer);
		}
	}
	
}
