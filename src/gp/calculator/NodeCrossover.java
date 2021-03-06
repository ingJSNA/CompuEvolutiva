package gp.calculator;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_2_2;

public class NodeCrossover extends Variation_2_2<Node> {

	@Override
	public Node[] apply(Node one, Node two) {
		Node childOne = one.clone(null);
		Node childTwo = two.clone(null);

		int c1 = RandomUtils.nextInt(0, childOne.weight());
		int c2 = RandomUtils.nextInt(0, childTwo.weight());

		Node n1 = childOne.get(c1);
		Node n2 = childTwo.get(c2);
		Node temp = n1.clone(n1.parent);

		n1.parent = n2.parent;
		if (n2.parent != null) {
			if (n2.equals(n2.parent.left)) {
				n2.parent.left = n1;
			} else if (n2.equals(n2.parent.right)) {
				n2.parent.right = n1;
			}
		}

		n2.parent = temp.parent;
		if (temp.parent != null) {
			if (temp.equals(temp.parent.left)) {
				temp.parent.left = n2;
			} else if (temp.equals(temp.parent.right)) {
				temp.parent.right = n2;
			}
		}

		return new Node[] { childOne, childTwo };
	}
}
