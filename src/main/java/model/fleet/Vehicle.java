package model.fleet;

import model.nodes.Location;

public class Vehicle extends MovingObject{	
	Truck truck;
	Trailer trailer;
	
	public Vehicle(Truck truck, Trailer trailer, Location loc) {
		super(truck.getCapacity() + trailer.getCapacity() , loc);
	}

	
}
