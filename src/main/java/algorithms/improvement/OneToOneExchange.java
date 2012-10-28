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
		
		return null;
	}

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
