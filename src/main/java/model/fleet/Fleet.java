package model.fleet;

import java.util.HashSet;
import java.util.Set;

public class Fleet {
	private double truckCapacity;
	private double trailerCapacity;
	
	Set<Truck> trucks = new HashSet<Truck>();
	Set<Trailer> trailers = new HashSet<Trailer>();
	Set<CompleteVehicle> completeVehicles;
	
	public Fleet(double truckCapacity, Set<Truck> trucks, double trailerCapacity, Set<Trailer> trailers) {
		this.truckCapacity = truckCapacity;
		this.trailerCapacity = trailerCapacity;
		this.trucks = trucks;
		this.trailers = trailers;
	}

	public Set<Truck> getTrucks() {
		return trucks;
	}

	public Set<Trailer> getTrailers() {
		return trailers;
	}

	public double getTruckCapacity() {
		return truckCapacity;
	}

	public double getTrailerCapacity() {
		return trailerCapacity;
	}
	
	
	
	
}
