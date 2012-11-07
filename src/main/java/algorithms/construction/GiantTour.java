package algorithms.construction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import model.Solution;
import model.TTRP;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.routes.Route;

public class GiantTour extends Route implements ConstructionHeuristic {

	private GiantTour(Depot d) {
		super(d);
	}
	
	private GiantTour(Depot d, Collection<Customer> customers) {
		super(d);
		super.customers.addAll(customers);
	}
	
	public static GiantTour createGreedyGiantTour(TTRP ttrp) {
		List<Customer> customers = new ArrayList<Customer>(ttrp.getCustomers());
		GiantTour greedyGiantTour = new GiantTour(ttrp.getDepot());
		
		Customer firstCustomer = Node.nearest(customers, ttrp.getDepot());
		greedyGiantTour.addCustomer(firstCustomer);
		
		while (Iterables.any(customers, Customer.notSatisfied())) {
			/*
			 * Get the unsatisfied customer who is nearest to the last serviced(visited) customer
			 */
			Customer nearestToTheLastVisitedOne = Node.nearest(Collections2.filter(customers, Customer.notSatisfied()), greedyGiantTour.getLastCustomer());
			greedyGiantTour.addCustomer(nearestToTheLastVisitedOne);
		}		
		return greedyGiantTour;
	}

	public Solution apply(TTRP input) {
		// TODO Auto-generated method stub
		return null;
	}
}
