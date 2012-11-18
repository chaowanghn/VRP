package algorithms.construction;

import static org.junit.Assert.*;

import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;

import org.junit.Before;
import org.junit.Test;

public class StringSolutionRepresentationTest extends ConctructionHeuristicTest {
	
	//================TRUCK CUSTOMERS=============
	TruckCustomer tc1 = new TruckCustomer(1, 10, null, false);
	TruckCustomer tc3 = new TruckCustomer(3, 15, null, false);
	TruckCustomer tc9 = new TruckCustomer(9, 10, null, false);
	TruckCustomer tc10 = new TruckCustomer(10, 10, null, false);
	TruckCustomer tc11 = new TruckCustomer(11, 10, null, false);

	//================VEHICLE CUSTOMERS===========
	VehicleCustomer vc2 = new VehicleCustomer(2, 10, null, false);
	VehicleCustomer vc4 = new VehicleCustomer(4, 40, null, false);
	VehicleCustomer vc5 = new VehicleCustomer(5, 20, null, false);
	VehicleCustomer vc6 = new VehicleCustomer(6, 30, null, false);
	VehicleCustomer vc7 = new VehicleCustomer(7, 10, null, false);
	VehicleCustomer vc8 = new VehicleCustomer(8, 15, null, false);
	VehicleCustomer vc12 = new VehicleCustomer(12, 15, null, false);
	VehicleCustomer vc13 = new VehicleCustomer(13, 30, null, false);
	VehicleCustomer vc14 = new VehicleCustomer(14, 20, null, false);
	VehicleCustomer vc15 = new VehicleCustomer(15, 20, null, false);
	
	double truckCapacity = 100;
	double trailerCapacity = 50;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		
	}

	@Override
	@Test
	public void apply() {
		fail("Not yet implemented");
	}

}
