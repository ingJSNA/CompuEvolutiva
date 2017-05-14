package gp.calculator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.optimization.OptimizationFunction;

public class NodeFitness extends OptimizationFunction<Node> {

	private static final Logger LOG = LoggerFactory.getLogger(NodeFitness.class);

	private List<Example> examples;

	public NodeFitness(List<Example> examples) {
		this.examples = new ArrayList<Example>(examples);
	}

	@Override
	public Double apply(Node x) {
		LOG.debug("Nodo: {}.", x);

		double diff = 0;
		for (Example e : examples) {
			diff += Math.abs(e.getAnswer() - x.evaluate(e.getX(), e.getY()));
		}

		// Calc the fitness
		double fitness = diff;
		fitness += ((double) x.weight()) / examples.size();

		return fitness;
	}

}
