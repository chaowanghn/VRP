package model.routes;

import model.fleet.Truck;
import model.nodes.Depot;
import model.nodes.TruckCustomer;

public class PureTruckRoute extends Route {

	public PureTruckRoute(Depot d) {
		super(d);
	}
	
	public PureTruckRoute(Depot d, Truck truck) {
		this(d);
		super.vehicle = truck;
	}

	public void addCustomer(TruckCustomer c) {
		super.customers.add(c);
	}

}
