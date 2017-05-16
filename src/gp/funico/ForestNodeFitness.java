package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.optimization.OptimizationFunction;

class ForestNodeFitness extends OptimizationFunction<ForestNode> {

	private static final Logger LOG = LoggerFactory.getLogger(ForestNodeFitness.class);

	private List<Example> examples;

	public ForestNodeFitness(List<Example> examples) {
		this.examples = new ArrayList<Example>(examples);
	}

	@Override
	public Double apply(ForestNode x) {
		LOG.debug("Nodo: {}.", x);

		double diff = 0;
		for (Example e : examples) {
			diff += Math.abs(e.getAnswer() - x.evaluate(e.getX(), e.getY()));
		}

		// Calc the fitness
		double fitness = diff;

		return fitness;
	}

}
