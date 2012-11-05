package model.nodes;

public class Customer extends Node {
	
	private static final long serialVersionUID = 1L;
	protected int id;
	protected double demand;
	protected Location location;
	protected boolean isSatisfied = false;
	
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

	@Override
	public double getX() {
		return this.location.getX();
	}

	@Override
	public double getY() {
		return this.location.getY();
	}
	
	
	
	
}
