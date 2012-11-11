package model.routes;

import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;

public class PureTruckRoute extends Route<Node,Customer,Truck> {

	public PureTruckRoute(Node d) {
		super(d);
	}
	
	public PureTruckRoute(Node d, Truck truck) {
		this(d);
		super.vehicle = truck;
	}
	
	public <T extends Customer> boolean feasibleInsertion(T c) {
		return c.getDemand() <= this.availableLoad();
	}
}
