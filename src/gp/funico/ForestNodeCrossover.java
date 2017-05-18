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
		EquationNode p1 = n1.parent; // TODO NULL PARENT
		EquationNode p2 = n2.parent;

		p1.replaceChild(n1, n2);

		p2.replaceChild(n2, n1);

		ArrayList<ForestNode> offspring = new ArrayList<ForestNode>();
		offspring.add(childOne);
		offspring.add(childTwo);

		return offspring;
	}

	private List<ForestNode> globalXOver(ForestNode one, ForestNode two) {
		// TODO Auto-generated method stub
		return new ArrayList<ForestNode>();
	}
}
