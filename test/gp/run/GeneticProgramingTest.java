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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticProgramingTest {

	private static final Logger LOG = LoggerFactory.getLogger(GeneticProgramingTest.class);

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
			FunicoFile example = FunicoFile.double_f;
			File file = FileUtils.getFile(example.getFilePath());
			ExampleReader reader = new ExampleReader(file);
			FunicoGP instance = FunicoGP.getInstance(reader).traceSearch(true)
					.setMaxIterations(500);

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
			FunicoFile[] values = FunicoFile.values();
			/*
			 * values = new FunicoFile[] { FunicoFile.mod3, FunicoFile.min, FunicoFile.sum,
			 * FunicoFile.geq };
			 */
			for (FunicoFile example : values) {
				File file = FileUtils.getFile(example.getFilePath());
				ExampleReader reader = new ExampleReader(file);
				int[] iterations = new int[] { 100, 200, 300, 400, 500 };

				for (int iter : iterations) {
					FunicoGP instance = FunicoGP.getInstance(reader).setMaxIterations(iter)
							.traceSearch(true).setTraceFile("./log/" + example + "-i" + iter);

					LOG.warn("Example: {}, Iterations: {}", example, iter);
					Double best = instance.evolve();
					assertTrue(best >= 0);
					LOG.warn("fitness: {}", best);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

	private FunicoGP buildFunico(String filepath) {
		File file = FileUtils.getFile(filepath);
		ExampleReader reader = new ExampleReader(file);
		FunicoGP instance = FunicoGP.getInstance(reader);
		return instance;
	}

}
