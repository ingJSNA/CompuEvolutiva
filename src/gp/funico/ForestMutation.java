package gp.funico;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_1_1;

public class ForestMutation extends Variation_1_1<Forest> {

	private static final double CHANGE_SIZE_PROBABILTY = 0.1;

	@Override
	public Forest apply(Forest gen) {
		gen = new Forest(gen);
		if (Math.random() < CHANGE_SIZE_PROBABILTY) {
			return changeSizeMutation(gen);
		} else {
			return mutationGP(gen);
		}
	}

	/**
	 * Change the size of the forest, adding or removing a tree
	 * 
	 * @param gen
	 * @return
	 */
	private Forest changeSizeMutation(Forest gen) {
		if (RandomUtils.nextBoolean()) {
			gen.addEquation();
		} else {
			gen.removeEquation();
		}
		return gen;
	}

	/**
	 * Change a random node of the given program.
	 * 
	 * @param gen
	 * @return
	 */
	private Forest mutationGP(Forest gen) {
		int e = RandomUtils.nextInt(0, gen.treesCount());

		EquationNode original = gen.getTree(e);
		int h = RandomUtils.nextInt(0, gen.getMaxNodesByEquation() / 2);
		EquationNode n1 = new EquationNode(gen.getExpression(), null, h);

		EquationNode p1 = original.parent;

		if (p1 == null) {
			gen.setTree(e, n1);
		} else {
			p1.replaceChild(original, n1);
		}

		return gen;
	}

}
