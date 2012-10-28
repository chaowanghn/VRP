package algorithms.construction;

import static org.junit.Assert.*;
import io.InstanceImporterTest;
import io.Visualizer;

import model.TTRP;

import org.junit.Before;
import org.junit.Test;

public class GiantTourTest {
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(InstanceImporterTest.inputInstanceFilepath);
	}

	@Test
	public void test() {
		GiantTour gt = GiantTour.createGreedyGiantTour(ttrp);
		Visualizer.visualizeRoute(gt);
	}

}
