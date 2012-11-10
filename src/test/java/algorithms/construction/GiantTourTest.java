package algorithms.construction;

import static org.junit.Assert.*;
import io.Visualizer;

import org.junit.Test;

public class GiantTourTest extends ConctructionHeuristicTest {

	@Test
	public void apply() {
		GiantTour greedyGiantTour = GiantTour.createGreedyGiantTour(ttrp);
		assertTrue(greedyGiantTour.getCustomers().size() == ttrp.getCustomers().size());
		System.out.println(greedyGiantTour);
		
		Visualizer.visualizeRoute(greedyGiantTour);
		
		while (1<2) {
			
		}
	}

}
