package model.nodes;


import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class Customer extends Node {
	
	private static final long serialVersionUID = 1L;
	protected int id;
	protected double demand;
	protected Location location;
	protected boolean isSatisfied = false;
	
	public Customer (int id, double demand, Location location, boolean isSatisfied) {
		this.id = id;
		this.demand = demand;
		this.location = location;
		this.isSatisfied = isSatisfied;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDemand() {
		return demand;
	}

	public void setDemand(double demand) {
		this.demand = demand;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isSatisfied() {
		return isSatisfied;
	}

	public void setSatisfied(boolean isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", demand=" + demand + ", location="
				+ location + ", isSatisfied=" + isSatisfied + "]";
	}

	@Override
	public double getX() {
		return this.location.getX();
	}

	@Override
	public double getY() {
		return this.location.getY();
	}
	
	public static Predicate<Customer> satisfied() {
		return new Predicate<Customer>(){public boolean apply(Customer c) {return c.isSatisfied;}};
	}
	
	public static Predicate<Customer> notSatisfied() {
		return Predicates.not(Customer.satisfied());
	}

	public static <C extends Customer> Collection<C> getNotSatisfied(Collection<C> customers){
		return Collections2.filter(customers, notSatisfied());
	}
	
	public static double totalDemand(Iterable<? extends Customer> customers) {
		double totalDemand=0;
		for(Customer c:customers){
			totalDemand += c.getDemand();
		}
		return totalDemand;
	}
	
	
}
