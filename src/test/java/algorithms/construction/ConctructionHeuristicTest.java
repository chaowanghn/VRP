package algorithms.construction;

import model.TTRP;

import org.junit.Before;
import org.junit.Test;

public abstract class ConctructionHeuristicTest {
	
	TTRP ttrp;

	@Before
	public void setUp() throws Exception {
		ttrp = TTRP.createInstanceFromFile(TTRP.TTRP_03_INSTANCE);
	}

	@Test
	public abstract void apply();

}
