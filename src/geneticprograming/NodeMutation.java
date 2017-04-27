package geneticprograming;

import unalcol.search.variation.Variation_1_1;

public class NodeMutation extends Variation_1_1<Node> {

	String[] code = new String[] { "-9", "-8" };

	@Override
	public Node apply(Node gen) {
		gen = gen.clone(null);

		int n = gen.weight();
		int m = ((int) Math.random() * n);
		Node mutate_node = gen.get(m);

		int x = (int) (Math.random() * code.length);
		mutate_node.oper = code[x];

		// TODO
		return null;
	}

}
