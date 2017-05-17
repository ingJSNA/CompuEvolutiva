package gp.run;

import static org.junit.Assert.*;

import java.io.File;

import gp.GeneticProgramming;
import gp.calculator.CalculatorGP;
import gp.funico.ExampleReader;
import gp.funico.FunicoGP;

import org.apache.commons.io.FileUtils;
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
			GeneticProgramming instance = CalculatorGP.getInstance();
			Double best = instance.evolve();
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
			File file = FileUtils.getFile("./data/Funico/then-example.txt");
			ExampleReader reader = new ExampleReader(file);
			GeneticProgramming instance = FunicoGP.getInstance(reader);
			Double best = instance.evolve();
			assertNotNull(best);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

}
