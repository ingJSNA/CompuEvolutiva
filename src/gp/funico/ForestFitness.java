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

		double maxEquationsfitness = 0;
		double equationCount = (double) x.equationCount();
		double maxEquations = (double) reader.getMaxEquations();

		if (x.equationCount() > maxEquations) {
			maxEquationsfitness = Math.abs(equationCount - maxEquations) / maxEquations;
		}

		double maxEquationTerms = (double) x.maxEquationTerms();
		double maxNodesByEquation = (double) reader.getMaxNodesByEquation();
		double maxEquationTermsfitness = 0;

		if (maxEquationTerms > maxNodesByEquation) {
			maxEquationTermsfitness = Math.abs(maxEquationTerms - maxNodesByEquation)
					/ maxNodesByEquation;
		}

		fitness = 0.8 * fitness + 0.2 * (maxEquationsfitness + maxEquationTermsfitness);

		LOG.info("Nodo: {}. Fitness: {}", x, fitness);
		return fitness;
	}

}
