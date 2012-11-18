package algorithms.improvement;

import static org.junit.Assert.*;

import model.fleet.Truck;
import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;

public class ZeroOneExchangeTest extends MoveTest{
	ZeroOneExchange zeroOneExchange;
	Route<Depot,Customer,Truck> route;

	@Before
	public void setUp() {
		super.setUp();
		route = new Route<Depot, Customer, Truck>(ttrp.getDepot(), Iterables.getFirst(ttrp.getFleet().getTrucks(), null));;
		route.addCustomers(ttrp.getCustomers(1,2,3,4));
		zeroOneExchange = new ZeroOneExchange();
	}

	@Override
	@Test
	public void testIntraRoute() {
		assertTrue(zeroOneExchange.apply(route).size() == 6);
	}

	@Override
	@Test
	public void testApplyToSolution() {
		// TODO Auto-generated method stub
		
	}

}
