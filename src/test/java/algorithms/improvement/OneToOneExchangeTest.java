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

	@Test
	public void test() {		
		Neighborhood neighborhood = oneToOneExchange.apply(initialSolution);
		assertEquals(neighborhood.getInitialMovable(), initialSolution);
		
	}

}
