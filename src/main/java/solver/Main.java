package solver;

import io.Visualizer;
import model.Solution;
import model.TTRP;

public class Main {

	public static void main(String[] args) {
		TTRP ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);
		//ttrp.visualize();
		Solution bestKnownSolution = Solution.createSolutionFromFile(TTRP.TTRP_03_INSTANCE, Solution.TTRP_03_BEST_KNOWN_SOLUTION);
		//bestKnownSolution.visualize();
		Visualizer.visualize(bestKnownSolution);
	}

}
