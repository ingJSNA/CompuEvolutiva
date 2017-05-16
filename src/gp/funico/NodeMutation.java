package gp.funico;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.variation.Variation_1_1;

class NodeMutation extends Variation_1_1<EquationNode> {

	@Override
	public EquationNode apply(EquationNode gen) {
		gen = gen.clone(null);

		int n = gen.weight();
		int m = RandomUtils.nextInt(0, n);
		EquationNode mutate_node = gen.get(m);

		// TODO
		String[] code = new String[] { "?" };
		int x = RandomUtils.nextInt(0, code.length);
		mutate_node.oper = code[x];

		return gen;
	}

}
