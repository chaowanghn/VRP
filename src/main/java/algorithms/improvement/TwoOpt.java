package algorithms.improvement;


public class TwoOpt implements Move {
	
	MoveConfiguration configuration;
	
	public TwoOpt() {
		
	}

	
	public Neighborhood apply(Movable input) {
		return null;
	}

	public void setConfiguration(MoveConfiguration config) {
		this.configuration = config;
	}
	
	public MoveConfiguration getConfiguration() {
		return this.configuration;
	}

}
