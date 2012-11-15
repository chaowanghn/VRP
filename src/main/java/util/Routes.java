package util;

import java.util.Collection;

import model.routes.Edge;
import model.routes.Route;

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

}
