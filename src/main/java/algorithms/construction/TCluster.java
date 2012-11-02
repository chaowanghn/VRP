package algorithms.construction;

import model.Solution;
import model.TTRP;
/*
 * Implement the T-Cluster construction heuristics as described in [3]
 * 
 * T-Cluster can be considered as a 
 * cluster-based sequential isntertion procedure, where routes are constructed one by one up to full vehicle
 * utilization.
 * 
 * A new route is initialized with the unrouted custome rus farthetst away from the depot and the
 * unused vehicle having mazimum total capacity. Thereby, complete vehicles are always preferred over
 * pure trucks. In cas of a complete vehicle, if the seed customer is a VC customer, then it is inserted into
 * the main-tour. On the other hand it is inserted into a new sub-tour to the depot, if it is a TC customer.
 * 
 * The next custoemr for insertion into the routes is thne selected from the unrouted customers minimizing
 * 		e(k) = c(ku) + c(kf) - πc(0k)
 * 		where
 * 		k specifies the customer under consideration
 * 		f the nearest customer already routed in this route, and 0 the depot. 
 * For customer k the formulae can be explained as follows:
 * 
 * 		The first term c(ku) measures the distance to the seed customer u of this route. This distance should be 
 * 		kept at minimum to ensure compact routes.
 * 
 * 		The second term c(kf) ensures the customer is close
 * 		to the "border" of the current route. This proves helpful in instances with clustered customer, where a
 * 		route should preferably serve all customers of the group before spreading to another group of customers.
 * 
 * 		Finally, the third term πc(0k) can be considered as a diversification term. Varyin the parameter π will result
 * 		in different selection strategies: the higher the value of π, the higher the tendency for a selection of a
 * 		customer located far away from the depot.
 * 		 
 */

public class TCluster implements ConstructionHeuristic {
	
	public static void createInitialTCluster(TTRP ttrp) {
		
	}

	public Solution apply(TTRP input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
