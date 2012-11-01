package model.routes;

import model.nodes.Depot;
import model.nodes.TruckCustomer;

public class PureTruckRoute extends Route {

	public PureTruckRoute(Depot d) {
		super(d);
	}

	public void addCustomer(TruckCustomer c) {
		super.customers.add(c);
	}

}
