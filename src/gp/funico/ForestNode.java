package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForestNode implements Cloneable {

	private static final Logger LOG = LoggerFactory.getLogger(ForestNode.class);

	private List<EquationNode> trees;

	public ForestNode(int maxEquations, int maxNodesByEquation, Expression expression) {
		trees = new ArrayList<EquationNode>();
		for (int i = RandomUtils.nextInt(0, maxEquations); i < maxEquations; i++) {
			trees.add(new EquationNode(expression, null, RandomUtils.nextInt(0, maxNodesByEquation)));
		}
	}

	/**
	 * Copy constructor
	 * 
	 * @param forest
	 */
	public ForestNode(ForestNode forest) {

		trees = new ArrayList<EquationNode>(forest.trees.size());
		for (EquationNode equation : forest.trees) {
			trees.add(new EquationNode(equation));
		}
	}

	// TODO
	public double evaluate(double x, double y) {
		return 0;
	}

	/**
	 * Number of equations
	 * 
	 * @return
	 */
	public int equationsCount() {
		return trees.size();
	}

	public EquationNode getEquation(int index) {
		return trees.get(index);
	}

	@Override
	public String toString() {
		String string = super.toString();
		return string;
	}

	/**
	 * Repair this object
	 */
	public void repair() {
		// TODO

	}
}
