package io;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class SolutionImporterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetIntFromString(){
		assertTrue(SolutionImporter.getIntFromString("blabla14blablamorebla256bla") == 14);
	}
}
