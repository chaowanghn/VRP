package io;

import static org.junit.Assert.*;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Solution;
import model.TTRP;
import model.routes.CompleteVehicleRoute;
import model.routes.PureTruckRoute;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;

public class SolutionImporterTest {
	SolutionImporter solutionImporter;
	TTRP ttrp;
	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);
		this.solutionImporter = new SolutionImporter(ttrp, new File(Solution.TTRP_03_BEST_KNOWN_SOLUTION));
		this.solutionImporter.read();
	}

	@Test
	public void test(){
		assertTrue(solutionImporter.getTotalCost() == 618.0372068925424);
		assertTrue(solutionImporter.getNumberOfVehicleRoutes() == 3);
		assertTrue(solutionImporter.getNumberOfTruckRoutes() == 2);
		assertTrue(solutionImporter.getNumberOfSubTours() == 6);
		assertTrue(solutionImporter.getNumberOfTruckRoutes() == 2 );
		assertTrue(solutionImporter.getNumberOfVehicleRoutes() == 3);
	
		Solution solution = Solution.createSolutionFromFile(ttrp, Solution.TTRP_03_BEST_KNOWN_SOLUTION);
		assertTrue(solution.cost() == solutionImporter.getTotalCost());
	}
	
	@Test
	public void testGetIntFromString(){
		assertTrue(SolutionImporter.getIntFromString("blabla14blablamorebla256bla") == 14);
	}
}
