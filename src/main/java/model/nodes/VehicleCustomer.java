package model.nodes;

public class VehicleCustomer extends Customer {

	public VehicleCustomer(int id, double demand, Location location,boolean isSatisfied) {
		super(id, demand, location, isSatisfied);
	}
	
	public String toString() {
		return "Vehicle " + super.toString();
	}
}
