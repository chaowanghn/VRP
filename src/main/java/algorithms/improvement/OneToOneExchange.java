package algorithms.improvement;

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
	
	public Neighborhood apply(Route initialRoute) {
		Neighborhood neighborhood = new Neighborhood(initialRoute);
		
		for(int i=1; i<=initialRoute.getCustomers().size()-1; i++) {
			for(int j=i+1; j<=initialRoute.getCustomers().size(); j++) {
				Route neighborRoute = new Route(initialRoute.getDepot(), initialRoute.getCustomers());
				neighborRoute.swap(i, j);
				neighborhood.addNeighbor(neighborRoute);
			}
		}
	
		return neighborhood;
	}

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
