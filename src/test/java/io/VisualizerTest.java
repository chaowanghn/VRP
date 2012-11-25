package io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Solution;
import model.TTRP;
import model.fleet.MovingObject;
import model.nodes.Customer;
import model.nodes.Node;
import model.nodes.VehicleCustomer;
import model.routes.CompleteVehicleRoute;
import model.routes.CompleteVehicleRoute;
import model.routes.Route;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;

import algorithms.improvement.TwoOpt;
import algorithms.improvement.ZeroOneExchange;

public class VisualizerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
	}
	
	public static void main(String[] args) throws IOException {
		TTRP ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);		
		
		CompleteVehicleRoute cvr = new CompleteVehicleRoute(ttrp.getDepot());
		cvr.addCustomer((VehicleCustomer) ttrp.getCustomer(6));
		cvr.addCustomer((VehicleCustomer) ttrp.getCustomer(24));
		cvr.addCustomer((VehicleCustomer) ttrp.getCustomer(43));
		cvr.addCustomer((VehicleCustomer) ttrp.getCustomer(48));
		cvr.addCustomer((VehicleCustomer) ttrp.getCustomer(27));

		SubTour st1 = new SubTour(ttrp.getNode(6));
		st1.addCustomer(ttrp.getCustomer(14));
		st1.addCustomer(ttrp.getCustomer(25));
		st1.addCustomer(ttrp.getCustomer(18));
		
		SubTour st2 = new SubTour(ttrp.getNode(48));
		st2.addCustomer(ttrp.getCustomer(26));
		st2.addCustomer(ttrp.getCustomer(31));
		st2.addCustomer(ttrp.getCustomer(28));
		st2.addCustomer(ttrp.getCustomer(8));
		cvr.addSubTour(st1);
		cvr.addSubTour(st2);
		
		List<Route<? extends Node,? extends Customer,? extends MovingObject>> routes = new ArrayList<>();
		routes.add(cvr);

		//Visualizer.visualize(new TwoOpt().apply(cvr));
		System.out.println(Solution.createSolutionFromFile(ttrp, Solution.TTRP_03_BEST_KNOWN_SOLUTION));
		Visualizer.visualize(Solution.createSolutionFromFile(ttrp, Solution.TTRP_03_BEST_KNOWN_SOLUTION));
	}

}
