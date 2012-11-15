package nodes;


import java.util.*;

import io.InstanceImporterTest;

import model.TTRP;
import model.nodes.Customer;
import model.nodes.Node;

import org.junit.Before;
import org.junit.Test;

import util.Nodes;

public class NodeTest {
	
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);
	}

	@Test
	public void test() {
		List<Customer> customers = new ArrayList<Customer>(ttrp.getCustomers());
		for (Node n : Nodes.inAscendingDistance(customers, ttrp.getDepot())) {
			System.out.println("Node id: " + n.getId() +" "+ n.distance(ttrp.getDepot()));
		}
		
		System.out.println("The nearest node is: " +  Nodes.nearest(customers, ttrp.getDepot()).getId());
		System.out.println("The farthest node is : " + Nodes.farthest(customers, ttrp.getDepot()).getId());
	}

}
