package algorithms;

import java.util.Collection;

import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

public class GiantTour extends Route {

	public GiantTour(Depot d) {
		super(d);
	}
	
	public GiantTour(Depot d, Collection<Customer> customers) {
		super(d);
		super.customers.addAll(customers);
	}
}
