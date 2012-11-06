package algorithms.improvement;

import static org.junit.Assert.*;

import java.util.List;

import model.Solution;
import model.TTRP;
import model.nodes.Customer;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public abstract class MoveTest {
	public static int[] DEFAULT_COUSTOMER_IDS = {3,6,2,5,1,4};
	TTRP ttrp;
	Solution initialSolution;
	Route route;
	List<Customer> customers;

	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
		this.initialSolution = new Solution();
		this.route = new Route(ttrp.getDepot());
		customers = ttrp.getCustomers(3,6,2,5,1,4);
		route.addCustomers(customers);
		initialSolution.addRoute(route);
	}

	
	@Test
	public abstract void testIntraRoute();
	
	@Test
	public abstract void testApplyToSolution();
}
