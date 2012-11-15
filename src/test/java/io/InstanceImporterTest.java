package io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import model.TTRP;

import org.junit.Before;
import org.junit.Test;

public class InstanceImporterTest {
	File inputInstanceFile;
	InstanceImporter importer;
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		inputInstanceFile = new File(TTRP.TTRP_03_INSTANCE);
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
