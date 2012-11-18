package algorithms.improvement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.routes.Route;


public class OneOneExchange implements Move {
	private MoveConfiguration configuration;
	
	public OneOneExchange() {
		
	}
	
	public OneOneExchange(MoveConfiguration config) {
		this.configuration = config;
	}
	

	public Neighborhood apply(Movable initial) {
		Neighborhood neighborhood = new Neighborhood(initial);
		return neighborhood;
	}
	
	public Neighborhood apply(Route<? extends Depot,? extends Customer,? extends MovingObject> initialRoute) {
		Neighborhood neighborhood = new Neighborhood(initialRoute);
		
		for(int i=0; i<initialRoute.getCustomers().size(); i++) {
			for(int j=i+1; j<initialRoute.getCustomers().size(); j++) {
				List<Customer> customers = new ArrayList<>(initialRoute.getCustomers());
				Collections.swap(customers, i, j);
				Route<Node,Customer,MovingObject> neighborRoute = new Route<Node, Customer, MovingObject>(initialRoute.getDepot(), customers, initialRoute.getVehicle());
				neighborhood.addNeighbor(neighborRoute);
			}
		}
	
		return neighborhood;
	}

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
