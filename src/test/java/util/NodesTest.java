package util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import model.nodes.*;

public class NodesTest {
	int[] indices = {3,5,8};
	List<Node> nodes = new ArrayList<Node>();
	Depot depot = new Depot(0, new Location(0,0));
	Customer c1 = new Customer(1, 0, null, false);
	Customer c2 = new Customer(2, 0, null, false);
	Customer c3 = new Customer(3, 0, null, false);
	Customer c4 = new Customer(4, 0, null, false);
	Customer c5 = new Customer(5, 0, null, false);
	Customer c6 = new Customer(6, 0, null, false);
	Customer c7 = new Customer(6, 0, null, false);
	Customer c8 = new Customer(6, 0, null, false);
	Customer c9 = new Customer(6, 0, null, false);
	

	@Before
	public void setUp() throws Exception {
		this.nodes.add(c1);
		this.nodes.add(c2);
		this.nodes.add(c3);
		this.nodes.add(depot);
		assertTrue(nodes.get(indices[0]).equals(depot));
		this.nodes.add(c4);
		this.nodes.add(depot);
		assertTrue(nodes.get(indices[1]).equals(depot));
		this.nodes.add(c5);
		this.nodes.add(c6);
		this.nodes.add(depot);
		assertTrue(nodes.get(indices[2]).equals(depot));
		this.nodes.add(c7);
		this.nodes.add(c8);
		this.nodes.add(c9);
		assertTrue(nodes.size() == 12);
		
	}

	@Test
	public void testPartition() {
		/*
		 * 1-2-3-0-4-0-5-6-0-7-8-9
		 */
		List<List<Node>> lists = Nodes.partition(nodes, indices);
		assertTrue(lists.get(0).size() == 3);
		assertTrue(lists.get(1).size() == 1);
		assertTrue(lists.get(2).size() == 2);
		
	}
	

	@Test
	public void testToString(){
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Customer(1, 0, null, false));
		nodes.add(new Customer(2, 0, null, false));
		nodes.add(new Customer(3, 0, null, false));
		assertEquals("1-2-3", Nodes.toString(nodes));		
		
	}
}
