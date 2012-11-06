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
	
	public Neighborhood apply(Route initial) {
		Neighborhood neighborhood = new Neighborhood(initial);
		return neighborhood;
	}

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
