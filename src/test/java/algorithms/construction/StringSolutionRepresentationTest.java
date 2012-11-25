package algorithms.construction;

import static org.junit.Assert.*;

import io.Visualizer;

import java.util.List;
import java.util.Set;

import model.fleet.CompleteVehicle;
import model.nodes.Depot;
import model.nodes.Location;
import model.nodes.Node;
import model.nodes.TruckCustomer;
import model.nodes.VehicleCustomer;
import model.routes.CompleteVehicleRoute;
import model.routes.PureTruckRoute;
import model.routes.Route;
import model.routes.SubTour;

import org.junit.Before;
import org.junit.Test;


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
		List<Node> permutation = stringRepresentation.getPermutation();
		assertTrue(permutation.containsAll(ttrp.getNodes()));
		assertTrue(stringRepresentation.getPotentialRoutes().size() == stringRepresentation.depotIndices(permutation).length+1);
		
	}

}
