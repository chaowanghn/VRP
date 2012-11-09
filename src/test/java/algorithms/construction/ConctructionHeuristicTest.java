package algorithms.construction;

import static org.junit.Assert.*;

import model.TTRP;

import org.junit.Before;
import org.junit.Test;

public abstract class ConctructionHeuristicTest {
	
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(TTRP.INPUT_FILE_PATH);
	}

	@Test
	public abstract void apply();

}
