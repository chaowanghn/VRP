package algorithms.improvement;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class OneOneExchangeTest extends MoveTest {
	OneOneExchange oneToOneExchange;

	@Before
	public void setUp(){
		super.setUp();
		oneToOneExchange = new OneOneExchange();
	}


	@Override
	@Test
	public void testIntraRoute() {
		int numberOfCustomer = route.getCustomers().size();
		assertTrue(numberOfCustomer == 6);
		
		Neighborhood neighborhood = oneToOneExchange.apply(route);
		
		assertEquals(neighborhood.getInitialMovable(), route);
		assertTrue(neighborhood.hasNeighbors());
		assertTrue("Neighborhood size: "+neighborhood.size(), neighborhood.size() == 15);
		System.out.println(neighborhood);
	}


	@Override
	@Test
	public void testApplyToSolution() {
		// TODO Auto-generated method stub
		
	}

}
