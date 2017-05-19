package gp.funico;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.optimization.OptimizationFunction;

public class ForestFitness extends OptimizationFunction<Forest> {

	private static final Logger LOG = LoggerFactory.getLogger(ForestFitness.class);

	private List<String> examples;

	public ForestFitness(List<String> list) {
		this.examples = list;
	}

	@Override
	public Double apply(Forest x) {

		double diff = 0;
		for (String goal : examples) {
			diff += Math.abs(x.evaluate(goal));
		}

		// Calc the fitness
		double fitness = diff / examples.size();

		LOG.info("Nodo: {}. Fitness: {}", x, fitness);
		return fitness;
	}

}
