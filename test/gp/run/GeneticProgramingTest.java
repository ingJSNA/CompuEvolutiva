package gp.run;

import static org.junit.Assert.*;
import gp.GeneticPrograming;
import gp.funico.FunicoGP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneticProgramingTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCalculator() {
		try {
			Double best = new GeneticPrograming().call();
			assertNotNull(best);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

	@Test
	public final void testFunico() {
		try {
			Double best = new FunicoGP().call();
			assertNotNull(best);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

}
