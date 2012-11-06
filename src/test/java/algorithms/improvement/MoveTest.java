package algorithms.improvement;

import static org.junit.Assert.*;

import model.Solution;
import model.TTRP;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class MoveTest {
	TTRP ttrp;
	Solution initialSolution;
	Route route;

	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
		this.initialSolution = new Solution();
		this.route = new Route(ttrp.getDepot());
	}

}
