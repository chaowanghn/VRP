package model.fleet;

import static com.google.common.base.Preconditions.checkState;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

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
	
	public Truck getAvailableTruckWithMaxCapacity() {
		checkState(hasAvailabeTrucks(),"there are no truck available");
		return Collections.max(Collections2.filter(this.trucks, MovingObject.available()));
	}
	
	public Trailer getAvailableTrailerWithMaxCapacity() {
		checkState(hasAvailabeTrailers(),"there are no trailers available");
		return Collections.max(Collections2.filter(this.trailers, MovingObject.available()));
	}
	
	public boolean hasAvailabeTrucks() {
		return Iterables.any(this.trucks, MovingObject.available());
	}
	
	public boolean hasAvailabeTrailers() {
		return Iterables.any(this.trailers, MovingObject.available());
	}

	public MovingObject getUnusedVehicleWithMaxCapacity() {
		if(this.hasAvailabeTrailers() && this.hasAvailabeTrucks()) {
			Trailer trailer = this.getAvailableTrailerWithMaxCapacity();
			Truck truck = this.getAvailableTruckWithMaxCapacity();
			return new CompleteVehicle(truck, trailer, null);
		}
		
		if(this.hasAvailabeTrucks()) {
			return this.getAvailableTruckWithMaxCapacity();
		}
		
		else return null;
	}
}
