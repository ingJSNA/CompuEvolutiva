package gp.funico;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_1_1;

public class ForestMutation extends Variation_1_1<Forest> {

	private static final double CHANGE_SIZE_PROBABILTY = 0.1;

	@Override
	public Forest apply(Forest gen) {
		gen = new Forest(gen);
		if (Math.random() < CHANGE_SIZE_PROBABILTY) {
			gen = changeSizeMutation(gen);
		} else {
			gen = mutationGP(gen);
		}

		return gen;
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
		EquationNode p1 = original.parent;

		int h = 0;
		EquationNode n1 = null;

		if (p1 == null) {
			h = RandomUtils.nextInt(0, (gen.getMaxNodesByEquation() / 2));
			n1 = new EquationNode(gen.getExpression(), null, h);
			gen.setTree(e, n1);
		} else {
			h = RandomUtils.nextInt(0, (gen.getMaxNodesByEquation() / 2) - p1.weight());
			n1 = new EquationNode(gen.getExpression(), null, h);
			p1.replaceChild(original, n1);
		}

		return gen;
	}

}
