package model.routes;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import model.nodes.Customer;
import model.nodes.Depot;

public class Route {

	private Depot depot;
	protected List<Customer> customers = new ArrayList<Customer>();
	
	public Route(Depot d) {
		this.depot = d;
	}
	
	public void addCustomer(Customer c) {
		customers.add(c);
	}
	
	public double cost() {
		double totalCost=0.0;
		
		totalCost += depot.distance(customers.get(0));
		
		for (int i=1; i<=customers.size()-1; i++) {
			totalCost += customers.get(i).distance(customers.get(i-1));
		}
		
		totalCost += depot.distance(customers.get(customers.size()-1));
		
		return totalCost;
	}
	
}
