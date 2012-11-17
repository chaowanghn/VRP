package model.routes;

import com.google.common.collect.ImmutableList;

import io.Visualizer;
import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.routes.Edge.EdgeType;

public class PureTruckRoute extends Route<Node,Customer,Truck> {

	public PureTruckRoute(Node d) {
		super(d);
	}
	
	public PureTruckRoute(Node d, Truck truck) {
		this(d);
		super.vehicle = truck;
	}
	
	public <T extends Customer> boolean feasibleInsertion(T c) {
		return c.getDemand() <= this.availableLoad();
	}
	
	public ImmutableList<Edge> getEdges(){
		ImmutableList<Edge> edges = super.getEdges();
		for(Edge e : edges){
			e.setType(EdgeType.TRUCK_EDGE);
		}
		return edges;
	}
	
}
