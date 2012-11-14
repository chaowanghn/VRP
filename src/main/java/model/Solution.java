package model;

import java.util.*;

import algorithms.improvement.Movable;

import model.routes.Route;

public class Solution implements Movable {
	public static String INPUT_FILE_PATH = "src/test/resources/instances/bestSolutions/sol-ttrp03.txt";

	
	List<Route<?,?,?>> routes;
	
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
	
	public boolean containsRoute(Route r) {
		return this.routes.contains(r);
	}

	
}


