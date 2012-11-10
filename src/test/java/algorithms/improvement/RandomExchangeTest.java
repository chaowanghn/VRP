package algorithms.improvement;


import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

import algorithms.improvement.MoveConfiguration;
import algorithms.improvement.RandomExchange;

public class RandomExchangeTest extends MoveTest {
	
	Route<?,Customer,?> route1;
	Route<?,Customer,?> route2;

	@Before
	public void setUp(){
		super.setUp();
		route1 = new Route<Depot,Customer,MovingObject>(ttrp.getDepot());
		route2 = new Route<Depot,Customer,MovingObject>(ttrp.getDepot());
		
		for (int i=1; i<=10; i++) {
			if (i<=5)
				route1.addCustomer(ttrp.getCustomer(i));	
			else 
				route2.addCustomer(ttrp.getCustomer(i));
		}
		
	}

	@Test
	public void test() {
		System.out.println(new RandomExchange(new MoveConfiguration(100000)).apply(route1).getBestNeigbor());
	}

	@Override
	@Test
	public void testIntraRoute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void testApplyToSolution() {
		// TODO Auto-generated method stub
		
	}

}
