package solver;

import algorithms.construction.GiantTour;
import model.TTRP;

public class Main {

	public static void main(String[] args) {
		TTRP ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
		GiantTour.createGreedyGiantTour(ttrp).visualize();
	}

}
