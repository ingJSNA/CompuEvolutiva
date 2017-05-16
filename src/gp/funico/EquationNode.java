package gp.funico;

import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.RandomCollectionsUtils;

class EquationNode {

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
	 * Copy constructor
	 * 
	 * @param other
	 */
	public EquationNode(EquationNode other) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Clone the current node with its children, and set the given parent.
	 * 
	 * @param parent
	 * @return
	 */
	public EquationNode clone(EquationNode parent) {
		EquationNode n = new EquationNode(parent, oper);
		if (!isLeaf()) {
			n.left = left.clone(n);
			n.right = right.clone(n);
		}
		return n;
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
		String string = "";
		if (this.isLeaf()) {
			string = this.oper;
		} else if ("*".equals(this.oper) && ("0".equals(left.oper) || "0".equals(right.oper))) {
			string = "0";
		}

		else if (GenericValidator.isDouble(left.oper) && GenericValidator.isDouble(right.oper)) {
			string = String.valueOf(this.evaluate(0, 0));
		} else {
			string += "(" + left + " " + this.oper + " " + right + ")";
		}
		return string;
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
