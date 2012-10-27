package algorithms.moves;

public class MoveConfiguration {
	
	private int numberOfIterations;
	
	public MoveConfiguration() {
		
	}

	public MoveConfiguration(int numOfIters) { 
		this.numberOfIterations = numOfIters;
	}
	
	public int getNumberOfIterations() {
		return this.numberOfIterations;
	}
		
	
}
