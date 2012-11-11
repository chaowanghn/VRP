package model.routes;

import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.TruckCustomer;

public class SubTour extends PureTruckRoute {

	public SubTour(Node d, Truck truck) {
		super(d, truck);
	}

	public SubTour(Node d) {
		super(d);
	}
	
	
	public Node getRoot() {
		return super.getDepot();
	}
	
	public <T extends Customer> boolean feasibleInsertion(T c) {
		if(c instanceof TruckCustomer) {
			return c.getDemand() <= this.availableLoad();
		}
		else {
			return false;
		}
		
	}
}
