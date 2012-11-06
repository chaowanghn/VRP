package algorithms.improvement;

import static org.junit.Assert.*;

import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class TwoOptTest extends MoveTest {
	TwoOpt twoOpt;
	Route route;
	@Before
	public void setUp() {
		super.setUp();
		route = new Route(ttrp.getDepot());
		route.addCustomers(ttrp.getCustomers(2,4,3,1,5));
		twoOpt = new TwoOpt();
	}

	@Override
	@Test
	public void testIntraRoute() {
		System.out.println(route);
		Neighborhood neighborhood = twoOpt.apply(route);
		assertEquals(neighborhood.getInitialMovable(), route);
		assertTrue(route.getCustomers().size() == 5);
		
	}

	@Override
	@Test
	public void testApplyToSolution() {
		
		
	}


}