package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_2_2;

public class ForestCrossover extends Variation_2_2<Forest> {

	@Override
	public Forest[] apply(Forest one, Forest two) {
		List<Forest> children = globalXOver(one, two);
		children.addAll(SubtreeXOver(one, two));
		return children.toArray(new Forest[children.size()]);
	}

	/**
	 * Make a crossover of sub trees.
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	private List<Forest> SubtreeXOver(Forest one, Forest two) {
		Forest childOne = new Forest(one);
		Forest childTwo = new Forest(two);

		int c1 = RandomUtils.nextInt(0, childOne.treesCount());
		int c2 = RandomUtils.nextInt(0, childTwo.treesCount());

		EquationNode n1 = childOne.getEquation(c1);
		EquationNode n2 = childTwo.getEquation(c2);
		EquationNode p1 = n1.parent;
		EquationNode p2 = n2.parent;

		if (p1 == null) {
			childOne.setTree(c1, n2);
		} else {
			p1.replaceChild(n1, n2);
		}

		if (p2 == null) {
			childTwo.setTree(c2, n1);
		} else {
			p2.replaceChild(n2, n1);
		}

		ArrayList<Forest> offspring = new ArrayList<Forest>();
		offspring.add(childOne);
		offspring.add(childTwo);

		return offspring;
	}

	private List<Forest> globalXOver(Forest one, Forest two) {
		// TODO Auto-generated method stub
		return new ArrayList<Forest>();
	}
}
