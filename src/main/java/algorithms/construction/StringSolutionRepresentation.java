package algorithms.construction;

import util.Customers;
import model.Solution;
import model.TTRP;

/*
 * Implements the construction heuristic based on a String representation of a TTRP solution
 * For implementation details see [9]
 * 
 * Description based on [9]
 * 
 * Customers are classified as vehicle-serviced(VCs) and
 * truck-serviced customers (TCs). A solution is represented by a string
 * of number consisting of a permutation of n customers denoted by
 * the set {1,2,...,n} and Ndummy zeros (artificial depot or the root
 * of a sub-tour), followed by the service vehicle types of individual
 * VCs. The Ndummy zeros are used to separate routes or terminate a 
 * sub-tour. The parameter Ndummy is calculated by [Σ(d_i)/Qk], where
 * [.] denote the largest integer which is smaller than or equal to the
 * enclosed number. The ith non-zero number is the first n+Ndummy
 * positions denotes the ith customer to be serviced.
 */

public class StringSolutionRepresentation implements ConstructionHeuristic{
	private int nDummy;

	@Override
	public Solution apply(TTRP ttrp) {
		this.nDummy = this.nDummy(ttrp);
		return null;
		
	}
	
	private int nDummy(TTRP ttrp){
		/*The parameter Ndummy is calculated by [Σ(d_i)/Qk], where
		 * [.] denote the largest integer which is smaller than or equal to the
		 * enclosed number. 
		 */ 
		return (int) Math.floor(Customers.totalDemand(ttrp.getCustomers()) / ttrp.getFleet().getTruckCapacity());
	}


}
