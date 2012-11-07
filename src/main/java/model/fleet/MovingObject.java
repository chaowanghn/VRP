package model.fleet;

import com.google.common.base.Predicate;

import model.nodes.Location;

public class MovingObject implements Comparable<MovingObject> {
	
	private double capacity; 
	private boolean isAvailable = true;
	private Location currentLocation;
	
	public MovingObject(double cap, Location loc) {
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

	public int compareTo(MovingObject o) {
		if (this.capacity < o.getCapacity())
			return -1;
		
		else if (this.capacity > o.getCapacity())
			return 1;
		else
			return 0;	
	}

	public static Predicate<MovingObject> available() {
		return new Predicate<MovingObject>() {
			public boolean apply(MovingObject mo){
				return mo.isAvailable();
			};
		};	
	}

}
