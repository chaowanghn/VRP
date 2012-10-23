package model.routes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;

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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(depot.getId());
		for (Customer c : customers)
			sb.append("-"+c.getId());
		sb.append("-"+depot.getId());
		return sb.toString();
	}
	
	public ImmutableList<Node> getNodes() {	
		return new ImmutableList.Builder<Node>().add(depot).addAll(customers).add(depot).build();
	}

	public void shuffleCustomers() {
		Collections.shuffle(customers);
	}


}
