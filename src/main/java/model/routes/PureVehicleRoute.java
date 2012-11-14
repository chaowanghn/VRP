package model.routes;

import model.fleet.CompleteVehicle;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.VehicleCustomer;

public class PureVehicleRoute extends Route<Depot,VehicleCustomer,CompleteVehicle> {

	public PureVehicleRoute(Depot d) {
		super(d);
	}
	
	public PureVehicleRoute(Depot d, CompleteVehicle vehicle) {
		this(d);
		super.vehicle = vehicle;
	}

	public void addCustomer(VehicleCustomer vCustomer) {
		super.customers.add(vCustomer);
		vCustomer.setSatisfied(true);
	}

	public <T extends Customer> boolean feasibleInsertion(T c){
		return (c.getDemand() <= this.availableLoad()) && ( c instanceof VehicleCustomer);
	}
	
}
