package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.nodes.Customer;
import model.nodes.Node;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class Customers {


	public static Predicate<Customer> satisfied() {
		return new Predicate<Customer>(){public boolean apply(Customer c) {return c.isSatisfied();}};
	}
	
	public static Predicate<Customer> notSatisfied() {
		return Predicates.not(Customers.satisfied());
	}

	public static <C extends Customer> Collection<C> getNotSatisfied(Collection<C> customers){
		return Collections2.filter(customers, notSatisfied());
	}
	
	public static <C extends Customer> Collection<C> getSatisfied(Collection<C> customers){
		return Collections2.filter(customers, satisfied());
	}
	
	public static double totalDemand(Iterable<? extends Customer> customers) {
		double totalDemand=0;
		for(Customer c:customers){
			totalDemand += c.getDemand();
		}
		return totalDemand;
	}

	public static <C extends Customer> List<C> getCustomers(Collection<? extends Node> nodes){
		List<C> customers = new ArrayList<C>();
		for(Node n : nodes){
			if(n instanceof Customer){
				customers.add((C) n);
			}
		}
		return customers;
	}


}
