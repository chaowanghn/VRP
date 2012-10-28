package algorithms.moves;

import io.Visualizer;
import model.nodes.Customer;
import model.routes.Route;

public class RandomExchange implements Move {
	
	private MoveConfiguration configuration;
	
	public RandomExchange(MoveConfiguration config) {
		this.configuration = config;
	}

	public Neighborhood apply(Movable initial) {
		Neighborhood neighborhood = new Neighborhood();
		
		if (initial instanceof Route) {
			Route initialRoute = (Route) initial;
			for (int i=1; i<=this.configuration.getNumberOfIterations(); i++) {
				Route neighbor = new Route(initialRoute.getDepot());
				initialRoute.shuffleCustomers();
				neighbor.setCustomers(initialRoute.getCustomers());
				neighborhood.addNeighbor(neighbor);
			}
			
		}
		return neighborhood;
	}

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

	

}
