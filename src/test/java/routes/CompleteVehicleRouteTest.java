package routes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.fleet.CompleteVehicle;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;
import model.routes.CompleteVehicleRoute;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;

public class CompleteVehicleRouteTest extends RouteTest {
	CompleteVehicleRoute cvr;
	List<VehicleCustomer> vehicleCustomers;
	List<TruckCustomer> truckCustomers;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.vehicleCustomers = new ArrayList<VehicleCustomer>(ttrp.getVehicleCustomers());
		this.truckCustomers = new ArrayList<TruckCustomer>(ttrp.getTruckCustomers());
		
		this.cvr = new CompleteVehicleRoute(ttrp.getDepot(), (CompleteVehicle) ttrp.getFleet().getUnusedVehicleWithMaxCapacity());
		
	}

	@Test
	public void testVisualize() {
		fail("Not yet implemented");
	}

}
