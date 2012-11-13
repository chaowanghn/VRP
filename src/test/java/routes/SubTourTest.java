package routes;

import static org.junit.Assert.*;

import java.util.*;

import model.TTRP;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;

import cern.colt.list.IntArrayList;

public class SubTourTest extends RouteTest {

	SubTour subTour;
	@Before
	public void setUp() throws Exception {
		super.setUp();
		assertFalse(super.vehicleCustomers.isEmpty());
	}

}
