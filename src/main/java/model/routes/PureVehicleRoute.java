package model.routes;

import model.nodes.Depot;
import model.nodes.VehicleCustomer;

public class PureVehicleRoute extends Route {

	public PureVehicleRoute(Depot d) {
		super(d);
	}

	public void addCustomer(VehicleCustomer vCustomer) {
		super.customers.add(vCustomer);
	}

}
