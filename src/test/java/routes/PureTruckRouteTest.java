package routes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.nodes.TruckCustomer;
import model.routes.PureTruckRoute;

import org.junit.Before;
import org.junit.Test;

public class PureTruckRouteTest extends RouteTest {
	PureTruckRoute ptr;
	List<TruckCustomer> truckCustomers;
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.truckCustomers = new ArrayList<TruckCustomer>(ttrp.getTruckCustomers());
		ptr = new PureTruckRoute(truckCustomers.get(1), null);
		for (int i=2; i<=5; i++){
			ptr.addCustomer(this.truckCustomers.get(i));
		}

	}

	@Test
	public void testVisualize() {
		
	}

}
