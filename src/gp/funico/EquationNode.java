package gp.funico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.RandomCollectionsUtils;

public class EquationNode {

	private static final Logger LOG = LoggerFactory.getLogger(EquationNode.class);

	private Expression expression;

	protected String oper;
	protected EquationNode parent;
	protected List<EquationNode> children;
	private int operArity;

	public EquationNode(Expression expression, EquationNode parent, int h) {
		this.parent = parent;
		this.expression = expression;
		if (h == 0) {
			oper = RandomCollectionsUtils.getItem(this.expression.getVariblesAndTerminals());
			this.children = Collections.emptyList();
		} else {
			oper = RandomCollectionsUtils.getItem(this.expression.getFunctions());
			this.operArity = this.expression.getFunctionArity(oper);
			this.children = new ArrayList<EquationNode>(operArity);

			for (int c = 0; c < operArity; c++) {
				children.add(new EquationNode(expression, this, h - 1));
			}
		}
	}

	public EquationNode(Expression expression, EquationNode parent, String oper) {
		this.parent = parent;
		this.oper = oper;
		this.expression = expression;
		this.operArity = this.expression.getFunctionArity(oper);
		this.children = new ArrayList<EquationNode>(operArity);
	}

	/**
	 * Clone the current node with its children, and set the given parent.
	 * 
	 * @param parent
	 * @return
	 */
	public EquationNode clone(EquationNode parent) {
		EquationNode clone = new EquationNode(expression, parent, oper);

		for (EquationNode equation : children) {
			clone.children.add(equation.clone(clone));
		}
		return clone;
	}

	private boolean isLeaf() {
		return this.children.isEmpty();
	}

	/**
	 * Number of nodes
	 * 
	 * @return
	 */
	public int weight() {
		int weight = 0;
		if (isLeaf()) {
			weight = 1;
		} else {
			weight = 1;
			for (EquationNode equation : children) {
				weight += equation.weight();
			}
		}
		return weight;
	}

	public EquationNode get(int index) {
		LOG.debug("index: {}. this: {}. weight: {}", index, this, weight());
		Validate.exclusiveBetween(-1, this.weight(), index);
		if (index == 0) {
			return this;
		} else {
			index--;
			for (EquationNode equation : children) {
				if (index < equation.weight()) {
					return equation.get(index);
				} else {
					index -= equation.weight();
				}
			}
		}
		return null;
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
			string = this.oper + "(";
			string += StringUtils.join(this.children, ", ");
			string += ")";
		}
		return string;
	}

	/**
	 * Repair this node
	 */
	public void repair() {
		while (children.size() < operArity) {
			String leafOper = RandomCollectionsUtils.getItem(expression.getVariblesAndTerminals());
			EquationNode leaf = new EquationNode(expression, this, leafOper);
			children.add(leaf);

		}
		for (EquationNode equation : children) {

			equation.repair();
		}
	}

	/**
	 * Replace a child node with the given replacement, keeping the original position.
	 * 
	 * @param child
	 * @param replacement
	 */
	public void replaceChild(EquationNode child, EquationNode replacement) {
		Validate.isTrue(this.children.contains(child));

		int index = this.children.indexOf(child);

		EquationNode removed = this.children.remove(index);
		removed.parent = null;

		this.children.add(index, replacement);
		replacement.parent = this;

	}
}
