package gp.funico;

import unalcol.search.variation.Variation_1_1;

class ForestNodeMutation extends Variation_1_1<ForestNode> {

	@Override
	public ForestNode apply(ForestNode gen) {
		gen = new ForestNode(gen);

		return gen;
	}

}
