package ga;

import static org.junit.Assert.*;

import java.util.Arrays;

import function.real.Ackley;
import ga.impl.CrossoverGeneticOperator;
import ga.impl.ElitismReplace;
import ga.impl.ImageSimilarity;
import ga.impl.SingleBitMutation;
import ga.impl.UniformSelection;
import ga.impl.OnePointCrossover;

import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticAlgorithmTest {

	private static final Logger LOG = LoggerFactory.getLogger(GeneticAlgorithmTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSolve() {
		int populationSize = 100;
		SelectionMethod selection = new UniformSelection();
		CrossoverMethod crossover = new OnePointCrossover();
		MutationMethod mutation = new SingleBitMutation();
		GeneticOperator geneticOperator = new CrossoverGeneticOperator(crossover, mutation);

		ReplaceMethod replace = new ElitismReplace();
		GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, selection, geneticOperator,
				replace);

		FitnessFunction fitness = new ImageSimilarity();

		Individual solution = ga.solve(fitness);
		assertNotNull(solution);
		System.out.println(solution.getArray());
	}

	@Test
	public final void testAckleyGA() {

		AckleyGA ga = new AckleyGA().traceSearch(true).setMaxIterations(50_000);
		Double solution;
		try {
			solution = ga.evolve();
			assertNotNull(solution);
			System.out.println(solution);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getClass().getSimpleName());
		}

	}

	@Test
	public final void testAckleyGAExperiment() {

		try {
			for (int run = 1; run <= 15; run++) {
				int iterations = 50_000;
				AckleyGA ga = new AckleyGA().traceSearch(true).setMaxIterations(iterations)
						.setTraceFile("./log/ga-r" + run + "-i" + iterations);
				Double solution = ga.evolve();
				assertNotNull(solution);
				System.out.println(solution);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getClass().getSimpleName());
		}

	}

	@Test
	public final void testAckley() {
		for (int i = 0; i < 100; i++) {
			double[] x = new double[] { RandomUtils.nextDouble(0, 32.768 * 2) - 32.768 };
			double myAckley = new Ackley().value(x);
			Double unalcolAckley = new unalcol.optimization.real.testbed.Ackley().apply(x);
			double diff = myAckley - unalcolAckley;
			LOG.warn("X: {}, diff: {}", Arrays.toString(x), diff);
		}
	}
}
