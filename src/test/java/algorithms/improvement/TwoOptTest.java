package algorithms.improvement;

import static org.junit.Assert.*;

import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class TwoOptTest extends MoveTest {
	TwoOpt twoOpt;
	Route<Depot,Customer,MovingObject> route;
	@Before
	public void setUp() {
		super.setUp();
		route = new Route<Depot, Customer, MovingObject>(ttrp.getDepot());
		route.addCustomers(ttrp.getCustomers(2,4,3,1,5));
		twoOpt = new TwoOpt();
	}

	@Override
	@Test
	public void testIntraRoute() {
		Neighborhood neighborhood = twoOpt.apply(route);
		assertEquals(neighborhood.getInitialMovable(), route);
		assertTrue(route.getCustomers().size() == 5);
		assertTrue(neighborhood.size()==10);
		
	}

	@Override
	@Test
	public void testApplyToSolution() {
		// TODO Auto-generated method stub
		
	}


}
