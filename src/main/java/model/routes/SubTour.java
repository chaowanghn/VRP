package model.routes;

import model.fleet.Truck;
import model.nodes.Depot;
import model.nodes.Node;

public class SubTour extends PureTruckRoute {

	public SubTour(Depot d, Truck truck) {
		super(d, truck);
	}

	public SubTour(Depot d) {
		super(d);
	}
	
	public Node getRoot() {
		return super.getDepot();
	}
	
}
