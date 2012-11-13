package routes;

import static org.junit.Assert.*;
import io.InstanceImporterTest;
import java.util.*;
import model.TTRP;
import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Depot;
import model.nodes.Node;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class RouteTest {

	TTRP ttrp; 
	Route<?,Customer,?> route;
	Depot depot;
	List<TruckCustomer> truckCustomers;
	List<VehicleCustomer> vehicleCustomers;
	
	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
		this.truckCustomers = new ArrayList<TruckCustomer>(ttrp.getTruckCustomers());
		this.vehicleCustomers = new ArrayList<VehicleCustomer>(ttrp.getVehicleCustomers());
		depot = ttrp.getDepot();
		route = new Route<Node,Customer,MovingObject>(ttrp.getCustomer(1));
	}




}
