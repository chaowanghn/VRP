package model.routes;

import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;

public class PureTruckRoute extends Route<Node,Customer,Truck> {

	public PureTruckRoute(Depot d) {
		super(d);
	}
	
	public PureTruckRoute(Depot d, Truck truck) {
		this(d);
		super.vehicle = truck;
	}
}
