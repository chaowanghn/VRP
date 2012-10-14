package model.nodes;

public class Depot extends Node {

	private static final long serialVersionUID = 1L;
	int id;
	Location location;
	
	public Depot(int id, Location loc) {
		this.id = id;
		this.location = loc;
	}

	public Location getLocation() {
		return location;
	}

	public int getId() {
		return id;
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
