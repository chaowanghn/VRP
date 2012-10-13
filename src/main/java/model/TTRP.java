package model;

import java.util.HashSet;
import java.util.Set;

import model.fleet.*;
import model.nodes.*;

/* PROBLEM OBJECTIVE:
 * 
 * The objective of the TTRP is to find a set of routes of minimum total distance such that:
 * 
 * # each customer is visited in a route performed by a compatible vehicle
 * 
 * # the total demand of the customers visited in a route does not exceed the capacity of the allocated vehicle
 * 
 * # the number of required trucks and trailers is not greater than the number of available trucks and trailers, respectively.
 * 
 * 
 */
public class TTRP {
	
	private double truckCapacity;
	
	private double trailerCapacity;
	
	private Depot depot;
	
	private Set<Customer> customers;
	private Set<TruckCustomer> truckCustomers;
	private Set<VehicleCustomer> vehicleCustomers;
	

	private Fleet fleet;
	
	private TTRP(double truckCapacity,double trailerCapacity,Depot depot, Set<Customer> customers) {
		super();
		this.truckCapacity = truckCapacity;
		this.trailerCapacity = trailerCapacity;
		this.depot = depot;
	} 

	public int getNumberOfCustomers() {
		return this.customers.size();
	}
	
	public int getNumberOfTruckCustomers() {
		return this.truckCustomers.size();
	}
	
	public int getNumberOfVehicelCustomers() {
		return this.vehicleCustomers.size();
	}
	
}