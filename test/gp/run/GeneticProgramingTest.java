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
			FunicoFile example = FunicoFile.then;
			GeneticProgramming instance = buildFunico(example.getFilePath());
			Double best = instance.evolve();
			assertTrue(best >= 0);
			assertTrue(best <= example.getBestFitness());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

	@Test
	public final void testAllFunico() {
		try {
			for (FunicoFile example : FunicoFile.values()) {
				GeneticProgramming instance = buildFunico(example.getFilePath());
				Double best = instance.evolve();
				assertTrue(best >= 0);
				assertTrue(best <= example.getBestFitness());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

	private GeneticProgramming buildFunico(String filepath) {
		File file = FileUtils.getFile(filepath);
		ExampleReader reader = new ExampleReader(file);
		GeneticProgramming instance = FunicoGP.getInstance(reader);
		return instance;
	}

}
