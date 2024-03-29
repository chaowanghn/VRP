package algorithms.improvement;


import java.util.List;

import model.TTRP;
import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public abstract class MoveTest {
	public static int[] DEFAULT_COUSTOMER_IDS = {3,6,2,5,1,4};
	TTRP ttrp;
	Route<Depot,Customer,MovingObject> route;
	List<Customer> customers;

	@Before
	public void setUp() {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);
		this.route = new Route<Depot,Customer,MovingObject>(ttrp.getDepot());
		customers = ttrp.getCustomers(3,6,2,5,1,4);
		route.addCustomers(customers);
	}

	
	@Test
	public abstract void testIntraRoute();
	
	@Test
	public abstract void testApplyToSolution();
}
