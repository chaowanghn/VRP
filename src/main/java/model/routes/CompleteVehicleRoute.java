package model.routes;

import java.util.HashSet;
import java.util.Set;

import model.fleet.CompleteVehicle;
import model.nodes.Depot;

public class CompleteVehicleRoute extends PureVehicleRoute {

	private Set<SubTour> subTours = new HashSet<SubTour>();
	
	public CompleteVehicleRoute(Depot d, CompleteVehicle vehicle) {
		super(d, vehicle);
	}

	public CompleteVehicleRoute(Depot d) {
		super(d);
	}
	
	public double cost() {
		// TODO Implement it!
		return 0;
	}
	
	public void addSubTour(SubTour st) {
		this.subTours.add(st);
	}

}
