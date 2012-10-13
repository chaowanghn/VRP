package model.nodes;

public class Customer implements Node {
	protected int id;
	protected double demand;
	protected Location location;
	protected boolean isSatisfied;
	
	public Customer (int id, double demand, Location location, boolean isSatisfied) {
		this.id = id;
		this.demand = demand;
		this.location = location;
		this.isSatisfied = isSatisfied;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDemand() {
		return demand;
	}

	public void setDemand(double demand) {
		this.demand = demand;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isSatisfied() {
		return isSatisfied;
	}

	public void setSatisfied(boolean isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", demand=" + demand + ", location="
				+ location + ", isSatisfied=" + isSatisfied + "]";
	}
	
	
	
	
}
