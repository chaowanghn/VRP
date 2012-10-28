package io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import model.TTRP;
import model.nodes.Location;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

public class InstanceImporterTest {
	public static String inputInstanceFilepath = "src/test/resources/instances/benchmark/ttrp01.dat";
	File inputInstanceFile;
	InstanceImporter importer;
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		inputInstanceFile = new File(inputInstanceFilepath);
		ttrp = TTRP.createInstanceFromFile(inputInstanceFile);
	}

	@Test
	public void test() throws IOException {
		assertTrue(ttrp.getFleet().getTruckCapacity() == 100);
		assertTrue(ttrp.getFleet().getTrucks().size() == 5);
		assertTrue(ttrp.getFleet().getTrailerCapacity() == 100);
		assertTrue(ttrp.getFleet().getTrailers().size() == 3);
		assertTrue(ttrp.getTruckCustomers().size() + ttrp.getVehicleCustomers().size() == 50 );
		assertTrue(ttrp.getCustomers().size() == 50);
		assertTrue(ttrp.getCustomers().containsAll(ttrp.getTruckCustomers()) && ttrp.getCustomers().containsAll(ttrp.getVehicleCustomers()));
	}

}
