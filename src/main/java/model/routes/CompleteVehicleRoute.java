package model.routes;

import model.fleet.CompleteVehicle;
import model.nodes.Depot;

public class CompleteVehicleRoute extends PureVehicleRoute {

	public CompleteVehicleRoute(Depot d, CompleteVehicle vehicle) {
		super(d, vehicle);
	}

	public CompleteVehicleRoute(Depot d) {
		super(d);
	}

}
