package nodes;

import static org.junit.Assert.*;

import java.util.*;

import io.InstanceImporterTest;

import model.TTRP;
import model.nodes.Customer;
import model.nodes.Node;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {
	
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		this.ttrp = TTRP.createInstanceFromFile(InstanceImporterTest.inputInstanceFilepath);
	}

	@Test
	public void test() {
		List<Customer> customers = new ArrayList<Customer>(ttrp.getCustomers());
		for (Node n : Node.nodesInAscendingDistance(customers, ttrp.getDepot())) {
			System.out.println("Node id: " + n.getId() +" "+ n.distance(ttrp.getDepot()));
		}
		
		System.out.println("The nearest node is: " +  Node.nearestNode(customers, ttrp.getDepot()).getId());
		System.out.println("The farthest node is : " + Node.farthestNode(customers, ttrp.getDepot()).getId());
	}

}
