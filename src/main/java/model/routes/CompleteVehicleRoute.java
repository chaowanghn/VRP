package model.routes;

import java.util.HashSet;
import java.util.Set;

import model.fleet.CompleteVehicle;
import model.nodes.Depot;
import model.nodes.VehicleCustomer;

public class CompleteVehicleRoute extends PureVehicleRoute {

	private Set<SubTour> subTours = new HashSet<SubTour>();
	
	public CompleteVehicleRoute(Depot d, CompleteVehicle vehicle) {
		super(d, vehicle);
	}

	public CompleteVehicleRoute(Depot d) {
		super(d);
	}
	
	public double cost() {
		double totalCost = 0;
		for(SubTour st : this.subTours) {
			totalCost += st.cost();
		}
		return totalCost + super.cost();
	}
	
	public void addSubTour(SubTour st) {
		this.subTours.add(st);
	}
	
	public void addToMainTour(VehicleCustomer vCustomer) {
		super.addCustomer(vCustomer);
	}

}
