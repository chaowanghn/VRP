package model.fleet;

import model.nodes.Location;

public class CompleteVehicle extends MovingObject{	
	Truck truck;
	Trailer trailer;
	
	public CompleteVehicle(Truck truck, Trailer trailer, Location loc) {
		super(truck.getCapacity() + trailer.getCapacity() , loc);
	}

	
}
