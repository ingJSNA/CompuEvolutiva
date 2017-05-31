package ga;

import static org.junit.Assert.*;
import ga.impl.CrossoverGeneticOperator;
import ga.impl.ElitismReplace;
import ga.impl.ImageSimilarity;
import ga.impl.SingleBitMutation;
import ga.impl.UniformSelection;
import ga.impl.OnePointCrossover;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneticAlgorithmTest {

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
	public final void testAckley() {
		AckleyGA ga = new AckleyGA().traceSearch(true);
		Double solution;
		try {
			solution = ga.call();
			assertNotNull(solution);
			System.out.println(solution);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getClass().getSimpleName());
		}

	}

}
