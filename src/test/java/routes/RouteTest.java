package routes;

import static org.junit.Assert.*;
import io.InstanceImporter;
import io.InstanceImporterTest;

import model.TTRP;
import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class RouteTest {

	TTRP ttrp; 
	Route route;
	Depot depot;
	
	@Before
	public void setUp() throws Exception {
		ttrp = InstanceImporter.createTTRP(InstanceImporterTest.inputInstanceFilepath);
		depot = ttrp.getDepot();
		route = new Route(ttrp.getDepot());
	}

	@Test
	public void test() {
		Customer c1 = (Customer) ttrp.nodesMap().get(1);
		route.addCustomer(c1);
		Customer c2 = (Customer) ttrp.nodesMap().get(2);
		route.addCustomer(c2);
		Customer c3 = (Customer) ttrp.nodesMap().get(3);
		route.addCustomer(c3);
		
		double routeCost = route.cost();
		double realCost = 
				depot.distance(c1)
				+ c1.distance(c2)
				+ c2.distance(c3)
				+ c3.distance(depot);
		
		assertTrue("Real Cost: "+realCost+ " Route Cost: " + routeCost, routeCost == realCost);
		assertEquals(route.toString(), "0-1-2-3-0");
		
		assertTrue(route.getNodes().contains(c1) && route.getNodes().contains(c2) && route.getNodes().contains(c3) && route.getNodes().contains(depot));
	}

}
