package model.fleet;

import java.util.HashSet;
import java.util.Set;

public class Fleet {
	
	Set<Truck> trucks;
	Set<Trailer> trailers;
	Set<Vehicle> completeVehicles;
	
	public Fleet(int numberOfTrucks, int numberOfTrailers) {
		this.trucks = new HashSet<Truck>(numberOfTrucks);
		this.trailers = new HashSet<Trailer>(numberOfTrailers);
		this.completeVehicles = new HashSet<Vehicle>();
	}


	public void addTruck(Truck truck) {
		trucks.add(truck);
	}
	
	public void addTrailer(Trailer trailer) {
		trailers.add(trailer);
	}
	
	public int getNumOfTrucks() {
		return trucks.size();
	}
	
	public int getNumOfTrailers() {
		return trailers.size();
	}
}
