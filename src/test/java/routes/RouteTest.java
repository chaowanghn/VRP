package routes;

import static org.junit.Assert.*;

import model.*;
import model.nodes.*;
import model.routes.*;
import model.fleet.*;
import org.junit.Before;
import org.junit.Test;

import algorithms.construction.GiantTour;

import util.Routes;

public class RouteTest {

	TTRP ttrp;
	Route<Node,Customer,MovingObject> route;
	
	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);
		this.route = new Route<Node, Customer, MovingObject>(ttrp.getDepot());
		route.addCustomers(ttrp.getCustomers(1,2,3,4));
	}

	@Test
	public void testGetEdges() {
		assertTrue(route.getEdges().size() == 5);
		assertTrue(Routes.costOfEdges(route.getEdges()) == route.cost());
	}

}
