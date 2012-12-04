package model.routes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import util.Customers;
import util.Nodes;

import com.google.common.collect.ImmutableList;

import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.TruckCustomer;
import model.routes.Edge.EdgeType;

public class SubTour extends PureTruckRoute {

	public SubTour(Node d, Truck truck) {
		super(d, truck);
	}

	public SubTour(Node d) {
		super(d);
	}
	
	public Node getRoot() {
		return super.getDepot();
	}
	
	public String toString(){
		return Nodes.toString(getNodes())+(" Load Used: "+this.utilRate()*100+" %");
	}
	
	
}
