package io;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.TTRP;
import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Node;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class VisualizerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
	}
	
	public static void main(String[] args) {
		TTRP ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
		
		Route<Node, Customer, MovingObject> route1 = new Route<Node, Customer, MovingObject>(ttrp.getDepot());
		route1.addCustomer(ttrp.getCustomer(18));
		route1.addCustomer(ttrp.getCustomer(14));
		route1.addCustomer(ttrp.getCustomer(24));
		route1.addCustomer(ttrp.getCustomer(43));
		route1.addCustomer(ttrp.getCustomer(7));
		route1.addCustomer(ttrp.getCustomer(23));
		route1.addCustomer(ttrp.getCustomer(6));
		
		Route<Node, Customer, MovingObject> route2 = new Route<Node, Customer, MovingObject>(ttrp.getDepot());
		route2.addCustomer(ttrp.getCustomer(12));
		route2.addCustomer(ttrp.getCustomer(5));
		route2.addCustomer(ttrp.getCustomer(38));
		route2.addCustomer(ttrp.getCustomer(11));
		route2.addCustomer(ttrp.getCustomer(2));
		route2.addCustomer(ttrp.getCustomer(3));
		route2.addCustomer(ttrp.getCustomer(32));
		
		List<Route<?,?,?>> routes = new ArrayList<Route<?,?,?>>();
		routes.add(route1);
		routes.add(route2);
		
		Visualizer.visualizeRoutes(ttrp.getAllNodes(), routes);
		
	}

}
