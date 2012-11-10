package algorithms.improvement;

import static org.junit.Assert.*;

import model.fleet.MovingObject;
import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;

public class TwoOptTest extends MoveTest {
	TwoOpt twoOpt;
	Route<Depot,Customer,Truck> route;
	
	@Before
	public void setUp() {
		super.setUp();
		route = new Route<Depot, Customer, Truck>(ttrp.getDepot(), Iterables.getFirst(ttrp.getFleet().getTrucks(), null));;
		route.addCustomers(ttrp.getCustomers(2,4,3,1,5));
		twoOpt = new TwoOpt();
	}

	@Override
	@Test
	public void testIntraRoute() {
		Neighborhood neighborhood = twoOpt.apply(route);
		assertNotNull(route);
		assertNotNull(route.getVehicle());
		assertNotNull(neighborhood);
		assertNotNull(neighborhood.getInitialMovable());
		
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
