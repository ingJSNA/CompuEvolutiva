package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Forest implements Cloneable {

	private static final Logger LOG = LoggerFactory.getLogger(Forest.class);

	private List<EquationNode> trees;

	public Forest() {
		trees = new ArrayList<EquationNode>();
	}

	/**
	 * Copy constructor
	 * 
	 * @param forest
	 */
	public Forest(Forest forest) {

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
