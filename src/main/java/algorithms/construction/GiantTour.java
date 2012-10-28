package algorithms.construction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import model.TTRP;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.routes.Route;

public class GiantTour extends Route {

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
		
		Predicate<Customer> notSatisfied = new Predicate<Customer>() {
			public boolean apply(Customer c) {
				return !c.isSatisfied();
			}
		};
		
		greedyGiantTour.addCustomer(Node.<Customer>nearestNode(customers, ttrp.getDepot()));
		
		while (Iterables.any(customers, notSatisfied)) { // while there are non-satisfied customers
			/*
			 * Get the unsatisfied customer who is nea
			 * rest to the last serviced(visited) customer
			 */
			Customer nearestCustomer = Node.nearestNode(Collections2.filter(customers, notSatisfied), greedyGiantTour.getLastCustomer());
			greedyGiantTour.addCustomer(nearestCustomer);
			nearestCustomer.setSatisfied(true);
		}		
		return greedyGiantTour;
	}
}
