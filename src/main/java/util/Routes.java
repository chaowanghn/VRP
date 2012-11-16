package util;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

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
	
	public static ImmutableSet<Edge> getAllEdges(Collection<? extends Route<?,?,?>> routes) {
		ImmutableSet.Builder<Edge> builder = new ImmutableSet.Builder<Edge>();
		for(Route<?,?,?> route : routes){
			builder.addAll(route.getEdges());
			if(route instanceof CompleteVehicleRoute) {
				if (((CompleteVehicleRoute) route).hasSubTours()) {
					for (SubTour st : ((CompleteVehicleRoute) route).getSubTours()) {
						builder.addAll(st.getEdges());
					}
				}
			}
		}
		return builder.build();
	}

}
