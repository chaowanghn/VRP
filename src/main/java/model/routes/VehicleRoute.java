package model.routes;

import model.nodes.Depot;
import model.nodes.VehicleCustomer;

public class VehicleRoute extends Route {

	public VehicleRoute(Depot d) {
		super(d);
	}

	public void addCustomer(VehicleCustomer vCustomer) {
		super.customers.add(vCustomer);
	}

}
