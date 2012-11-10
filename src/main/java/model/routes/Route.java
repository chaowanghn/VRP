package model.routes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import algorithms.improvement.Movable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Node;

public class Route<N extends Node,C extends Customer, V extends MovingObject> implements Movable {
	
	protected V vehicle;
	private N depot;
	protected List<C> customers;
	
	public Route(N d) {
		this.depot = d;
		this.customers = new ArrayList<C>();
	}
	
	public Route(N d, Collection<? extends C> customers) {
		this(d);
		this.customers.addAll(customers);
	}
	
	public void addCustomer(C c) {
		checkNotNull(c);
		c.setSatisfied(true);
		customers.add(c);
	}
	
	public double cost() {
		double totalCost=0.0;
		
		totalCost += depot.distance(Iterables.getFirst(customers, null));
		
		for (int i=1; i<=customers.size()-1; i++) {
			totalCost += customers.get(i).distance(customers.get(i-1));
		}
		
		totalCost += depot.distance(Iterables.getLast(customers));
		
		return totalCost;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(depot.getId());
		for (Customer c : customers)
			sb.append("-"+c.getId());
		sb.append("-"+depot.getId());
		sb.append("  Cost: " + cost());
		return sb.toString();
	}
	
	public ImmutableList<Node> getNodes() {	
		return new ImmutableList.Builder<Node>().add(depot).addAll(customers).add(depot).build();
	}

	public void shuffleCustomers() {
		Collections.shuffle(customers);
	}

	public int compareTo(Movable o) {
		if (this.cost() < o.cost())
			return -1;		
		else if (this.cost() > o.cost())
			return 1;		
		else 
			return 0;
	}

	public N getDepot() {
		return this.depot;
	}
	
	public List<C> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<C> customers) {
		this.customers = customers;
	}

	public static double costOfRoutes(Collection<? extends Route<?,?,?>> routes) {
		double totalCost = 0;
		for (Route<?,?,?> r : routes)
			totalCost += r.cost();
		return totalCost;
	}

	public Customer getLastCustomer() {
		return Iterables.getLast(this.customers);
	}

	public MovingObject getVehicle() {
		return vehicle;
	}

	public boolean containsCustomer(Customer c) {
		return this.customers.contains(c);
	}
	
	public void swapCustomers(int i, int j) {
		Collections.swap(this.customers, --i, --j);
	}

	public void swapNodes(int i, int j) throws Exception {
		throw new Exception("not implemented yet!");
	}
	
	public static boolean feasibleInsertion(Route<?,?,?> route, Customer customer) {
		return false;
		
	}
	
	public double availableLoad(){
		return vehicle.getCapacity() - Customer.totalDemand(customers);
	}

	public void addCustomers(List<C> custs) {
		this.customers.addAll(custs);
	}
}
