package gp;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Node {

	private static final Logger LOG = LoggerFactory.getLogger(Node.class);

	protected String oper;
	protected Node left;
	protected Node right;
	protected Node parent;

	// TODO how initialize?
	private static Expression expression = new Expression();

	public Node(Node parent, int h) {
		this.parent = parent;
		if (h == 0) {
			String[] variableAndTerminals = expression.getVariblesAndTerminals();
			oper = variableAndTerminals[RandomUtils.nextInt(0, variableAndTerminals.length)];
			left = null;
			right = null;
		} else {
			String[] functions = expression.getFunctions();
			oper = functions[RandomUtils.nextInt(0, functions.length)];
			left = new Node(this, h - 1);
			right = new Node(this, h - 1);
		}
	}

	public Node(Node parent, String oper) {
		this.parent = parent;
		this.oper = oper;
	}

	/**
	 * Clone the current node with its children, and set the given parent.
	 * 
	 * @param parent
	 * @return
	 */
	public Node clone(Node parent) {
		Node n = new Node(parent, oper);
		if (!isLeaf()) {
			n.left = left.clone(n);
			n.right = right.clone(n);
		}
		return n;
	}

	private boolean isLeaf() {
		return left == null;
	}

	public int evaluate(int x, int y) {
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
			return Integer.parseInt(oper);
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

	public Node get(int index) {
		LOG.debug("index: {}. left: {}. right: {}. weight: {}", index, left, right, weight());
		if (index == 0) {
			return this;
		} else {
			index--;
			int wLeft = left == null ? -1 : left.weight();
			if (index <= wLeft) {
				return left.get(index);
			} else {
				return right.get(index - wLeft);
			}
		}
	}
}
