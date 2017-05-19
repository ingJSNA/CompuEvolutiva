package gp.funico;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_1_1;

public class ForestNodeMutation extends Variation_1_1<ForestNode> {

	private static final double CHANGE_SIZE_PROBABILTY = 0.1;

	@Override
	public ForestNode apply(ForestNode gen) {
		gen = new ForestNode(gen);
		if (Math.random() < CHANGE_SIZE_PROBABILTY) {
			return changeSizeMutation(gen);
		} else {
			return mutationGP(gen);
		}
	}

	private ForestNode changeSizeMutation(ForestNode gen) {
		if (RandomUtils.nextBoolean()) {
			gen.addEquation();
		} else {
			gen.removeEquation();
		}
		return gen;
	}

	private ForestNode mutationGP(ForestNode gen) {

		return gen;
	}

}
