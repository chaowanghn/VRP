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


	@Test
	public void testVisualize(){
		this.subTour = new SubTour(vehicleCustomers.get(0), null);
		System.out.println(vehicleCustomers.get(0).getId());
		
		this.subTour.addCustomer(vehicleCustomers.get(1));
		this.subTour.addCustomer(truckCustomers.get(1));
		this.subTour.addCustomer(vehicleCustomers.get(2));
		this.subTour.addCustomer(truckCustomers.get(2));
		this.subTour.addCustomer(vehicleCustomers.get(3));
		this.subTour.addCustomer(truckCustomers.get(3));
		
		subTour.visualize();
		
		while (1<2){
			
		}
		
	}
}
