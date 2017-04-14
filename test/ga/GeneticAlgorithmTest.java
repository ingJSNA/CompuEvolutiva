package ga;

import static org.junit.Assert.*;
import ga.impl.CrossoverGeneticOperator;
import ga.impl.ImageSimilarity;
import ga.impl.SingleBitMutation;
import ga.impl.UniformSelection;
import ga.impl.XOver;

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
		CrossoverMethod crossover = new XOver();
		MutationMethod mutation = new SingleBitMutation();
		GeneticOperator geneticOperator = new CrossoverGeneticOperator(crossover, mutation);
		GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, selection, geneticOperator);
		FitnessFunction fitness = new ImageSimilarity();
		assertNotNull(ga.solve(fitness));
	}

}
