package gp.funico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.RandomCollectionsUtils;

public class EquationNode {

	private static final Logger LOG = LoggerFactory.getLogger(EquationNode.class);

	private Expression expression;

	protected String oper;
	protected EquationNode left;
	protected EquationNode right;
	protected EquationNode parent;

	public EquationNode(Expression expression, EquationNode parent, int h) {
		this.parent = parent;
		this.expression = expression;
		if (h == 0) {
			oper = RandomCollectionsUtils.getItem(this.expression.getVariblesAndTerminals());
			left = null;
			right = null;
		} else {
			oper = RandomCollectionsUtils.getItem(this.expression.getFunctions());
			left = new EquationNode(expression, this, h - 1);
			right = new EquationNode(expression, this, h - 1);
		}
	}

	public EquationNode(EquationNode parent, String oper) {
		this.parent = parent;
		this.oper = oper;
	}

	/**
	 * Clone the current node with its children, and set the given parent.
	 * 
	 * @param parent
	 * @return
	 */
	public EquationNode clone(EquationNode parent) {
		EquationNode clone = new EquationNode(parent, oper);
		clone.expression = expression;

		clone.left = left.clone(clone);
		clone.right = right.clone(clone);
		return clone;
	}

	private boolean isLeaf() {
		return left == null;
	}

	public double evaluate(double x, double y) {
		switch (oper) {
		case "*":
			return left.evaluate(x, y) * right.evaluate(x, y);
		case "+":
			return left.evaluate(x, y) + right.evaluate(x, y);
		case "X":
			return x;
		case "Y":
			return y;

		default:
			return Double.parseDouble(oper);
		}
	}

	/**
	 * Number of nodes
	 * 
	 * @return
	 */
	public int weight() {
		if (isLeaf()) {
			return 1;
		} else {
			return 1 + left.weight() + right.weight();
		}
	}

	public EquationNode get(int index) {
		LOG.debug("index: {}. this: {}. left: {}. right: {}. weight: {}", index, this, left, right,
				weight());
		if (index == 0) {
			return this;
		} else {
			index--;
			int wLeft = left == null ? 0 : left.weight();
			if (index < wLeft) {
				return left.get(index);
			} else {
				return right.get(index - wLeft);
			}
		}
	}

	@Override
	public String toString() {
		return getSource();
	}

	public String getSource() {
		String string = "";
		if (expression.getVariblesAndTerminals().contains(this.oper)) {
			return this.oper;
		} else if (expression.getFunctions().contains(this.oper)) {
			return this.oper + "(" + left.getSource() + ", " + right.getSource() + ")";
		}
		return string.toString();
	}

	/**
	 * Repair this node
	 */
	public void repair() {

		if (this.isLeaf() && expression.getFunctions().contains(oper)) {
			this.left = new EquationNode(this, RandomCollectionsUtils.getItem(this.expression
					.getVariblesAndTerminals()));
			this.right = new EquationNode(this, RandomCollectionsUtils.getItem(this.expression
					.getVariblesAndTerminals()));
		} else if (!this.isLeaf() && expression.getVariblesAndTerminals().contains(oper)) {
			this.left = null;
			this.right = null;
		} else {
			if (left != null) {
				left.repair();
			}
			if (right != null) {
				right.repair();
			}
		}

	}
}
