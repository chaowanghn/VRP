package model.nodes;

public class TruckCustomer extends Customer{

	public TruckCustomer(int id, double demand, Location location,boolean isSatisfied) {
		super(id, demand, location, isSatisfied);
	}

	public String toString() {
		return "Truck "+super.toString();
	}
}
