package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.optimization.OptimizationFunction;

public class ForestNodeFitness extends OptimizationFunction<ForestNode> {

	private static final Logger LOG = LoggerFactory.getLogger(ForestNodeFitness.class);

	private List<String> examples;

	public ForestNodeFitness(List<String> list) {
		this.examples = list;
	}

	@Override
	public Double apply(ForestNode x) {
		LOG.debug("Nodo: {}.", x);

		double diff = 0;
		for (String goal : examples) {
			diff += examples.size() - x.evaluate(goal);
		}

		// Calc the fitness
		double fitness = diff / examples.size();

		return fitness;
	}

}
