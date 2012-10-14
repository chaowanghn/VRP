package model;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

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
	
	private Depot depot;
	
	private Set<TruckCustomer> truckCustomers;
	private Set<VehicleCustomer> vehicleCustomers;

	private Fleet fleet;
	
	public TTRP(Depot depot, Set<TruckCustomer> truckCustomers, Set<VehicleCustomer> vehicleCustomers, Fleet fleet) {
		super();
		this.truckCustomers = truckCustomers;
		this.vehicleCustomers = vehicleCustomers;
		this.depot = depot;
		this.fleet = fleet;
	}

	
	public Depot getDepot() {
		return depot;
	}

	public Set<TruckCustomer> getTruckCustomers() {
		return truckCustomers;
	}

	public Set<VehicleCustomer> getVehicleCustomers() {
		return vehicleCustomers;
	}

	public Fleet getFleet() {
		return fleet;
	} 
	
	public Set<Customer> getCustomers() {
		return Sets.union(truckCustomers, vehicleCustomers);
	}
	
	public Set<Node> getAllNodes() {
		Set<Node> allNodes = new HashSet<Node>();
		allNodes.addAll(getCustomers());
		allNodes.add(depot);
		return allNodes;
		
	}
	
}