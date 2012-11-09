package routes;

import static org.junit.Assert.*;

import java.util.Set;

import model.TTRP;
import model.nodes.TruckCustomer;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;

import cern.colt.list.IntArrayList;

public class SubTourTest {

	TTRP ttrp;
	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);	
	}

	@Test
	public void test() {
		SubTour st = new SubTour(ttrp.getNode(0));
		Set<TruckCustomer> truckCustomers = ttrp.getTruckCustomers();
		IntArrayList truckCustomerIds = new IntArrayList();
		for (TruckCustomer tc : truckCustomers) {
			truckCustomerIds.add(tc.getId());
		}
		TruckCustomer t1 = (TruckCustomer) ttrp.getCustomer(truckCustomerIds.get(1));
		TruckCustomer t2 = (TruckCustomer) ttrp.getCustomer(truckCustomerIds.get(2));
		TruckCustomer t3 = (TruckCustomer) ttrp.getCustomer(truckCustomerIds.get(3));
	
		st.addCustomer(t1);
		st.addCustomer(t2);
		st.addCustomer(t3);
		
		System.out.println(st);
		//assertTrue(st.cost() == (ttrp.getNode(0).distance(t1) + t1.distance(t2) + t2.distance(t3) + t3.distance(ttrp.getNode(0))));
		System.out.println(st.cost());
		System.out.println(ttrp.getNode(0).distance(t1) + t1.distance(t2) + t2.distance(t3) + t3.distance(ttrp.getNode(0)));
	}

}
