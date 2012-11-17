package util;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Node;
import model.routes.CompleteVehicleRoute;
import model.routes.Edge;
import model.routes.Route;
import model.routes.SubTour;

public class Routes {

	public static double costOfRoutes(Collection<? extends Route<?,?,?>> routes) {
		double totalCost = 0;
		for (Route<?,?,?> r : routes)
			totalCost += r.cost();
		return totalCost;
	}
	
	public static double costOfEdges(Collection<Edge> edges){
		double totalCost = 0;
		for(Edge e : edges){
			totalCost+=e.cost();
		}
		return totalCost;
	}
	
	public static ImmutableSet<Edge> getAllEdges(Collection<? extends Route<? extends Node,? extends Customer,? extends MovingObject>> routes) {
		ImmutableSet.Builder<Edge> builder = new ImmutableSet.Builder<Edge>();
		for(Route<? extends Node,? extends Customer,? extends MovingObject> route : routes){
			builder.addAll(route.getEdges());
		}
		return builder.build();
	}

	public static ImmutableSet<Node> getAllNodes(Collection<? extends Route<? extends Node,? extends Customer,? extends MovingObject>> routes) {
		ImmutableSet.Builder<Node> builder = new ImmutableSet.Builder<Node>();
		for(Route<? extends Node,? extends Customer,? extends MovingObject> route : routes){
			builder.addAll(route.getNodes());
		}
		return builder.build();
	}
}
