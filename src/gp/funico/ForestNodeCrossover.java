package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_2_2;

public class ForestNodeCrossover extends Variation_2_2<ForestNode> {

	@Override
	public ForestNode[] apply(ForestNode one, ForestNode two) {
		List<ForestNode> children = globalXOver(one, two);
		children.addAll(SubtreeXOver(one, two));
		return children.toArray(new ForestNode[children.size()]);
	}

	private List<ForestNode> SubtreeXOver(ForestNode one, ForestNode two) {
		ForestNode childOne = new ForestNode(one);
		ForestNode childTwo = new ForestNode(two);

		int c1 = RandomUtils.nextInt(0, childOne.equationsCount());
		int c2 = RandomUtils.nextInt(0, childTwo.equationsCount());

		EquationNode n1 = childOne.getEquation(c1);
		EquationNode n2 = childTwo.getEquation(c2);
		EquationNode temp = n1.clone(n1.parent);

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
		return null;
	}

	private List<ForestNode> globalXOver(ForestNode one, ForestNode two) {
		// TODO Auto-generated method stub
		return new ArrayList<ForestNode>();
	}
}
