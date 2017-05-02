package gp;

public class Node {

	protected String oper;
	protected Node left;
	protected Node right;
	protected Node parent;

	public Node(Node parent, int h) {
		this.parent = parent;
		if (h == 0) {
			// TODO
			oper = "Variable o número";
			left = null;
			right = null;
		} else {
			// TODO
			oper = "Operador";
			left = new Node(this, h - 1);
			right = new Node(this, h - 1);
		}
	}

	public Node(Node parent, String oper) {
		this.parent = parent;
		this.oper = oper;
	}

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
		if (index == 0) {
			return this;
		} else {
			index--;
			int wLeft = left.weight();
			if (index <= wLeft) {
				return left.get(index);
			} else {
				return right.get(index - wLeft);
			}
		}
	}
}
