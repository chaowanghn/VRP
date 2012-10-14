package model.nodes;

public class VehicleCustomer extends Customer {

	private static final long serialVersionUID = 1L;

	public VehicleCustomer(int id, double demand, Location location,boolean isSatisfied) {
		super(id, demand, location, isSatisfied);
	}
	
	public String toString() {
		return "Vehicle " + super.toString();
	}
}
