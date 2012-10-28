package model;

import java.util.*;

import algorithms.improvement.Movable;

import model.routes.Route;

public class Solution implements Movable {
	List<Route> routes = new LinkedList<Route>();
	
	public Solution() {
		
	}

	public int compareTo(Movable o) {
		if (this.cost() < o.cost())
			return -1;	
		else if (this.cost() > o.cost())
			return 1;	
		else 
			return 0;
	}

	public double cost() {
		return Route.costOfRoutes(routes);
	}
	
	public void addRoute(Route r) {
		this.routes.add(r);
	}
}
