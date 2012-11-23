package algorithms.construction;

import static org.junit.Assert.*;

import java.util.Set;

import model.fleet.CompleteVehicle;
import model.nodes.Depot;
import model.nodes.Location;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;
import model.routes.CompleteVehicleRoute;
import model.routes.PureTruckRoute;
import model.routes.Route;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;

import util.Nodes;

public class StringSolutionRepresentationTest extends ConctructionHeuristicTest {
	private StringSolutionRepresentation stringRepresentation;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.stringRepresentation = new StringSolutionRepresentation();
	}

	@Override
	@Test
	public void apply() {
		stringRepresentation.apply(ttrp);
		assertTrue(stringRepresentation.getPermutation().containsAll(ttrp.getNodes()));
	}

}
