package model.nodes;

public class Depot implements Node {
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
}
