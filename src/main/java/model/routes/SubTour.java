package model.routes;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;

import model.nodes.Customer;
import model.nodes.Node;
import model.nodes.TruckCustomer;

public class SubTour {
	private Node rootNode;
	List<TruckCustomer> customers = new ArrayList<TruckCustomer>();
	public SubTour(Node rootNode) {
		this.rootNode = rootNode;
	}

	public double cost() {
		double customerTraversingCost=0;
		for(int i=1; i<customers.size(); i++) {
			customerTraversingCost+=customers.get(i).distance(customers.get(i-1));
		}
		return rootNode.distance(Iterables.getFirst(customers, null)) + customerTraversingCost + Iterables.getLast(customers).distance(rootNode);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rootNode.getId());
		for (Customer c: customers) {
			sb.append("-" + c.getId());
		}
		sb.append("-"+rootNode.getId());
		return sb.toString();
	}
	
	public void addCustomer(TruckCustomer tc) {
		this.customers.add(tc);
	}
}
