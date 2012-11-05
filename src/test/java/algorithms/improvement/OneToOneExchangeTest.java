package algorithms.improvement;

import static org.junit.Assert.*;

import java.util.List;

import model.Solution;
import model.TTRP;
import model.nodes.Customer;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class OneToOneExchangeTest {
	TTRP ttrp;
	Solution initialSolution;
	Route route;

	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
		this.initialSolution = new Solution();
		this.route = new Route(ttrp.getDepot());
			
	}

	@Test
	public void test() {
		List<Customer> customers = ttrp.getCustomers(3,6,2,5,1,4);
		assertFalse(customers.isEmpty());
		route.addCustomers(customers);
		initialSolution.addRoute(route);
		assertTrue(initialSolution.containsRoute(route));
	}

}
