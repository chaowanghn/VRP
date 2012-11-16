package model;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import io.InstanceImporter;
import io.Visualizable;
import io.Visualizer;

import java.io.File;
import java.io.IOException;
import java.util.*;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import model.fleet.*;
import model.nodes.*;
import model.routes.Edge;

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
public class TTRP implements Visualizable {
	
	public static String TTRP_03_INSTANCE = "src/test/resources/instances/benchmark/ttrp03.dat";
		
	private Depot depot;
	
	private Set<TruckCustomer> truckCustomers;
	private Set<VehicleCustomer> vehicleCustomers;

	private Fleet fleet;
	
	private TTRP(Depot depot, Set<TruckCustomer> truckCustomers, Set<VehicleCustomer> vehicleCustomers, Fleet fleet) {
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
	
	public Set<Node> getNodes() {
		Set<Node> allNodes = new HashSet<Node>();
		allNodes.addAll(getCustomers());
		allNodes.add(depot);
		return allNodes;	
	}
	
	public String toString() {
		return "TTRP instance: Truck Customers: "+ this.truckCustomers.size()+" Vehicle Customers: " + this.vehicleCustomers.size() ;
	}
	
	public TIntObjectMap<Node> nodesMap() {
		TIntObjectHashMap<Node> map = new TIntObjectHashMap<Node>();
		for (Node n : getNodes()) {
			map.put(n.getId(), n);
		}
		return map;
	}

	public Node getNode(int id) {
		return nodesMap().get(id);
	}
	
	public Customer getCustomer(final int id) {
		return Iterables.getFirst(Collections2.filter(getCustomers(), new Predicate<Customer>() {
			public boolean apply(Customer c) {
				return c.getId() == id;
			}
		}), null); 
		
	}

	public List<Customer> getCustomers(int...ids) {
		List<Customer> customers = new ArrayList<Customer>();
		for (int id : ids) {
			customers.add(getCustomer(id));
		}
		return customers;
	}
	
	public static TTRP createInstanceFromFile(File inputFile) {
		InstanceImporter instanceImporter = new InstanceImporter(inputFile);
		try {
			instanceImporter.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TTRP(instanceImporter.getDepot(), instanceImporter.getTruckCustomers(), instanceImporter.getVehicleCustomers(), instanceImporter.getFleet());
		
	}
	
	public static TTRP createInstanceFromFile(String inputFilePath) {
		return createInstanceFromFile(new File(inputFilePath));
	}

	@Override
	public Collection<Edge> getEdges() {
		return new ArrayList<Edge>();
	}
}