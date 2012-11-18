package model.routes;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import io.Visualizable;
import io.Visualizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import util.Customers;
import algorithms.improvement.Movable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Node;
import model.routes.Edge.EdgeType;

public class Route<N extends Node,C extends Customer, V extends MovingObject> implements Movable,Visualizable {
	
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
	
	public Route(N d, V vehicle) {
		this(d);
		this.vehicle = vehicle;
	}
	
	public Route(N d, Collection<? extends C> customers, V vehicle) {
		this(d,customers);
		this.vehicle = vehicle;
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
	
	@Override
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

	public Customer getLastCustomer() {
		return Iterables.getLast(this.customers);
	}

	public MovingObject getVehicle() {
		return vehicle;
	}

	public boolean containsCustomer(Customer c) {
		return this.customers.contains(c);
	}
	
	public double totalDemand(){
		return Customers.totalDemand(this.customers);
	}
	
	public double availableLoad(){
		return vehicle.getCapacity() - this.totalDemand();
	}

	public C getFirstCustomer() {
		return Iterables.getFirst(this.customers, null);
	}
	
	public void addCustomers(List<C> custs) {
		this.customers.addAll(custs);
	}

	public ImmutableList<Edge> getEdges(){
		ImmutableList.Builder<Edge> builder = new ImmutableList.Builder<Edge>();
		builder.add(new Edge(this.depot,this.getFirstCustomer()));
		for(int i=1; i<this.customers.size(); i++){
			Edge e = new Edge(customers.get(i-1),customers.get(i));
			builder.add(e);
		}
		builder.add(new Edge(this.getLastCustomer(), this.depot));
		return builder.build();
	}
	
	public <T extends Customer> boolean feasibleInsertion(T c){
		return c.getDemand() < this.availableLoad();
	}

	@Override
	public String getVisualizationTitle() {
		return new StringBuilder().append("Route Cost: " + this.cost()).toString();
	}

	@Override
	public Movable getCopy() {
		return new Route<N,C,V>(this.depot,this.customers,this.vehicle);
	}

}
