package algorithms.moves;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.InstanceImporter;
import io.InstanceImporterTest;
import io.Visualizer;

import model.TTRP;
import model.nodes.Customer;
import model.routes.Route;

import org.junit.Before;
import org.junit.Test;

public class RandomExchageTest {
	
	public static String INPUT_FILE_PATH = "src/test/resources/instances/benchmark/ttrp06.dat";
	TTRP ttrp ;
	Route route1;
	Route route2;

	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(INPUT_FILE_PATH);
		route1 = new Route(ttrp.getDepot());
		route2 = new Route(ttrp.getDepot());
		
		for (int i=1; i<=10; i++) {
			if (i<=5) {
				route1.addCustomer((Customer) ttrp.nodesMap().get(i));
				
			}
			
			else {
				route2.addCustomer((Customer) ttrp.nodesMap().get(i));
			}
		}
		
	}

	@Test
	public void test() {
		System.out.println(new RandomExchange(new MoveConfiguration(100000)).apply(route1).getBestNeigbor());
	}

}
