package algorithms.construction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import model.TTRP;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.routes.Route;

public class GiantTour extends Route {

	public GiantTour(Depot d) {
		super(d);
	}
	
	public GiantTour(Depot d, Collection<Customer> customers) {
		super(d);
		super.customers.addAll(customers);
	}
	
	public static GiantTour createGreedyGiantTour(TTRP ttrp) {
		List<Customer> customers = new ArrayList<Customer>(ttrp.getCustomers());
		GiantTour gt = new GiantTour(ttrp.getDepot());
		
		Predicate<Customer> notSatisfied = new Predicate<Customer>() {
			public boolean apply(Customer c) {
				return !c.isSatisfied();
			}
		};
		
		gt.addCustomer(Node.<Customer>nearestNode(customers, ttrp.getDepot()));
		
		while (! Collections2.filter(customers, notSatisfied).isEmpty()) {
			Customer nearestCustomer = Node.nearestNode(Collections2.filter(customers, notSatisfied), gt.getLastCustomer());
			gt.addCustomer(nearestCustomer);
			nearestCustomer.setSatisfied(true);
		}		
		return gt;
	}
}
