package algorithms.improvement;

import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.routes.Route;


public class OneToOneExchange implements Move {
	private MoveConfiguration configuration;
	
	public OneToOneExchange() {
		
	}
	
	public OneToOneExchange(MoveConfiguration config) {
		this.configuration = config;
	}
	

	public Neighborhood apply(Movable initial) {
		Neighborhood neighborhood = new Neighborhood(initial);
		return neighborhood;
	}
	
	public Neighborhood apply(Route<? extends Depot,? extends Customer,? extends MovingObject> initialRoute) {
		Neighborhood neighborhood = new Neighborhood(initialRoute);
		
		for(int i=1; i<=initialRoute.getCustomers().size()-1; i++) {
			for(int j=i+1; j<=initialRoute.getCustomers().size(); j++) {
				Route<Node,Customer,MovingObject> neighborRoute = (Route<Node, Customer, MovingObject>) initialRoute.getCopy();
				neighborRoute.swapCustomers(i, j);
				neighborhood.addNeighbor(neighborRoute);
			}
		}
	
		return neighborhood;
	}

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
