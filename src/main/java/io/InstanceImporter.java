package io;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.TTRP;
import model.fleet.Fleet;
import model.fleet.Trailer;
import model.fleet.Truck;
import model.nodes.*;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class InstanceImporter {
	public static int VEHICLE_CUSTOMER_ID = 0;
	public static int TRUCK_CUSTOMER_ID = 1;
	
	private double truckCapacity;
	private int numberOfAvailabelTrucks;
	private double trailerCapacity;
	private int numberOfAvailableTrailers;
	private int numberOfCustomers;
	
	private Depot depot;
	private Set<TruckCustomer> truckCustomers = new HashSet<TruckCustomer>();
	private Set<VehicleCustomer> vehicleCustomers = new HashSet<VehicleCustomer>();
	
	private Fleet fleet;
	
	private File inputInstanceFile;
	
	private InstanceImporter (File f) {
		this.inputInstanceFile = f;
	}
	
	private void read() throws IOException {
		List<String> fileLines = Files.readLines(inputInstanceFile, Charsets.UTF_8);
		readFirstLine(fileLines.get(0).split("	"));
		readDepotLine(fileLines.get(1).split("	"));
		
		//remove first and second line so that fileLines contains only customer lines
		fileLines.remove(0);
		fileLines.remove(0);
		for (String customerLine : fileLines) {
			readCustomerLine(customerLine.split("	"));
		}
		
		createFleet();
	}
	
	private void readFirstLine(String[] firstLine)  throws IOException {
		this.truckCapacity = Double.parseDouble(firstLine[0].trim());
		this.numberOfAvailabelTrucks = Integer.parseInt(firstLine[1]);
		this.trailerCapacity = Double.parseDouble(firstLine[2]);
		this.numberOfAvailableTrailers = Integer.parseInt(firstLine[3]);
		this.numberOfCustomers = Integer.parseInt(firstLine[4]);
	}

	private void readDepotLine(String[] depotLine) throws IOException {
		this.depot = new Depot(Integer.parseInt(depotLine[0]), new Location(Double.parseDouble(depotLine[1]), Double.parseDouble(depotLine[2])));
	}
	
	private void readCustomerLine(String[] customerLine) throws IOException {
		int id = Integer.parseInt(customerLine[0]);
		double x = Double.parseDouble(customerLine[1]);
		double y = Double.parseDouble(customerLine[2]);
		double demand = Double.parseDouble(customerLine[3]);
		int customerType = Integer.parseInt(customerLine[4]);
		if (customerType == TRUCK_CUSTOMER_ID)
			this.truckCustomers.add(new TruckCustomer(id, demand, new Location(x,y), false));
		if (customerType == VEHICLE_CUSTOMER_ID)
			this.vehicleCustomers.add(new VehicleCustomer(id, demand, new Location(x,y), false));
	}
	
	private void createFleet() {
		Set<Truck> trucks = new HashSet<Truck>();
		for (int i=1; i<=this.numberOfAvailabelTrucks; i++) {
			trucks.add(new Truck(this.truckCapacity, depot.getLocation()));
		}
		
		Set<Trailer> trailers = new HashSet<Trailer>();
		for (int i=1; i<=this.numberOfAvailableTrailers; i++) {
			trailers.add(new Trailer(this.trailerCapacity, depot.getLocation()));
		}
		
		this.fleet = new Fleet(this.truckCapacity,trucks,this.trailerCapacity,trailers);
	}
	
	private Depot getDepot() {
		return depot;
	}

	private Set<TruckCustomer> getTruckCustomers() {
		return truckCustomers;
	}
	
	private Set<VehicleCustomer> getVehicleCustomers() {
		return vehicleCustomers;
	}
	
	public static TTRP createTTRP(File inputFile) throws IOException {
		InstanceImporter instanceImporter = new InstanceImporter(inputFile);
		instanceImporter.read();
		TTRP ttrp = new TTRP(instanceImporter.getDepot(), instanceImporter.getTruckCustomers(), instanceImporter.getVehicleCustomers(), instanceImporter.getFleet());
		return ttrp;
	}
	
	public static TTRP createTTRP(String filePath) throws IOException {
		return createTTRP(new File(filePath));
	}

	private Fleet getFleet() {
		return fleet;
	}
	

}
