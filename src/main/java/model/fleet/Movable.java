package model.fleet;

import model.nodes.Location;

public class Movable {
	
	private double capacity; 
	private boolean isAvailable = true;
	private Location currentLocation;
	
	public Movable(double cap, Location loc) {
		this.capacity = cap;
		this.currentLocation = loc;
	}
	
	public void setAvailable(boolean exp) {
		this.isAvailable = exp;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	public void setCurrentLocation(Location loc) {
		currentLocation = loc; 
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	
}
