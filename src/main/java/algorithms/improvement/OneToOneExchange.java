package algorithms.improvement;


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

	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
