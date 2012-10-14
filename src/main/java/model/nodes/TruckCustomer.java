package model.nodes;

public class TruckCustomer extends Customer{

	private static final long serialVersionUID = 1L;

	public TruckCustomer(int id, double demand, Location location,boolean isSatisfied) {
		super(id, demand, location, isSatisfied);
	}

	public String toString() {
		return "Truck "+super.toString();
	}
}
