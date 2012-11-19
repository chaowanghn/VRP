package algorithms.construction;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Customers;
import model.Solution;
import model.TTRP;
import model.nodes.VehicleCustomer;

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
 * 
 */

public class StringSolutionRepresentation implements ConstructionHeuristic{
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private int nDummy;
	Map<VehicleCustomer, ServiceType> vcsServiceType = new HashMap<VehicleCustomer, ServiceType>();

	@Override
	public Solution apply(TTRP ttrp) {
		checkNotNull(ttrp);
		this.nDummy = this.nDummy(ttrp); logger.info("Ndummy parameter for "+ttrp.toString()+" calculated and set to: "+this.nDummy);
		
		return null;
		
	}
	
	private int nDummy(TTRP ttrp){
		/*The parameter Ndummy is calculated by [Σ(d_i)/Qk], where
		 * [.] denote the largest integer which is smaller than or equal to the
		 * enclosed number. 
		 */ 
		return (int) Math.floor(Customers.totalDemand(ttrp.getCustomers()) / ttrp.getFleet().getTruckCapacity());
	}

	public static enum ServiceType{
		COMPLETE_VEHICLE,TRUCK
	}
	
}
