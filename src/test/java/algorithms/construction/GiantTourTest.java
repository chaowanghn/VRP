package algorithms.construction;

import static org.junit.Assert.*;

import model.TTRP;

import org.junit.Before;
import org.junit.Test;

public class GiantTourTest {
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
	}

	@Test
	public void test() {
		GiantTour greedyGiantTour = GiantTour.createGreedyGiantTour(ttrp);
		assertTrue(greedyGiantTour.getCustomers().size() == ttrp.getCustomers().size());
		System.out.println(greedyGiantTour);
	}

}
