package algorithms.improvement;

import static org.junit.Assert.*;

import java.util.List;

import model.Solution;
import model.TTRP;
import model.nodes.Customer;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class OneToOneExchangeTest extends MoveTest {
	OneToOneExchange oneToOneExchange;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		oneToOneExchange = new OneToOneExchange();
	}


	@Override
	@Test
	public void testApplyToSingleRoute() {
		int numberOfCustomer = route.getCustomers().size();
		assertTrue
		(numberOfCustomer == 6);
		
		Neighborhood neighborhood = oneToOneExchange.apply(route);
		
		assertEquals(neighborhood.getInitialMovable(), route);
		assertTrue(neighborhood.hasNeighbors());
		assertTrue("Neighborhood size: "+neighborhood.size(), neighborhood.size() == 15);
	}

	@Override
	@Test
	public void testApplyToSolution() {
		Neighborhood neighborhood = oneToOneExchange.apply(initialSolution);
		assertEquals(neighborhood.getInitialMovable(), initialSolution);
		
	}

}
