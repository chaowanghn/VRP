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
