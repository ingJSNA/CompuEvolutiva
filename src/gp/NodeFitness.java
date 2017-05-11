package gp;

import java.util.ArrayList;
import java.util.List;

import unalcol.optimization.OptimizationFunction;

public class NodeFitness extends OptimizationFunction<Node> {

	private List<Example> examples;

	public NodeFitness(List<Example> examples) {
		this.examples = new ArrayList<Example>(examples);
	}

	@Override
	public Double apply(Node x) {
		double diff = 0;
		for (Example e : examples) {
			diff += Math.abs(e.getAnswer() - x.evaluate(e.getX(), e.getY()));
		}
		return diff;
	}

}