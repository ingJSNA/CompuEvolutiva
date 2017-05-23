package gp.funico;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.optimization.OptimizationFunction;

public class ForestFitness extends OptimizationFunction<Forest> {

	private static final Logger LOG = LoggerFactory.getLogger(ForestFitness.class);

	private ExampleReader reader;

	public ForestFitness(ExampleReader reader) {
		this.reader = reader;
	}

	@Override
	public Double apply(Forest x) {

		double diff = 0;
		List<String> examples = reader.getExamples();
		for (String goal : examples) {
			diff += Math.abs(x.evaluate(goal));
		}

		// Calc the fitness
		double fitness = diff / examples.size();

		double maxEquationsfitness = Math.abs((double) x.equationCount()
				- (double) reader.getMaxEquations())
				/ (double) reader.getMaxEquations();

		double maxEquationTermsfitness = Math.abs((double) x.maxEquationTerms()
				- (double) reader.getMaxNodesByEquation())
				/ (double) reader.getMaxNodesByEquation();

		fitness = 0.8 * fitness + 0.1 * maxEquationsfitness + 0.1 * maxEquationTermsfitness;

		LOG.info("Nodo: {}. Fitness: {}", x, fitness);
		return fitness;
	}

}
