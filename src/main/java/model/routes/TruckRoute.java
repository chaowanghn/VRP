package model.routes;

import model.nodes.Depot;
import model.nodes.TruckCustomer;

public class TruckRoute extends Route {

	public TruckRoute(Depot d) {
		super(d);
	}

	public void addCustomer(TruckCustomer c) {
		super.customers.add(c);
	}

}
