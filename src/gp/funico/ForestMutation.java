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

	private Forest changeSizeMutation(Forest gen) {
		if (RandomUtils.nextBoolean()) {
			gen.addEquation();
		} else {
			gen.removeEquation();
		}
		return gen;
	}

	private Forest mutationGP(Forest gen) {
		// TODO
		return gen;
	}

}
