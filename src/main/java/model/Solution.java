package model;

import java.util.*;

import com.google.common.collect.ImmutableList;

import algorithms.improvement.Movable;

import model.fleet.MovingObject;
import model.routes.*;
import model.nodes.*;

public class Solution implements Movable {
	public static String INPUT_FILE_PATH = "src/test/resources/instances/bestSolutions/sol-ttrp03.txt";

	
	List<Route<? extends Node, ? extends Customer, ? extends MovingObject>> routes = new ArrayList<Route<? extends Node, ? extends Customer, ? extends MovingObject>>();
	
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
	
	public void addRoute(Route<Node,Customer,MovingObject> r) {
		this.routes.add(r);
	}
	
	public void addAll(List<? extends Route<? extends Node,? extends Customer,? extends MovingObject>> pureTruckRoutes) {
		this.routes.addAll(pureTruckRoutes);
	}
	
	public boolean containsRoute(Route<Node,Customer,MovingObject> r) {
		return this.routes.contains(r);
	}

	public List<Route<? extends Node, ? extends Customer, ? extends MovingObject>> getRoutes() {
		//List<Route<? extends Node, ? extends Customer, ? extends MovingObject>> routes = new ArrayList<Route<? extends Node, ? extends Customer, ? extends MovingObject>>();
		return this.routes;
	}

	
	
}


