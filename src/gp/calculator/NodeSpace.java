package gp.calculator;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.space.Space;

public class NodeSpace extends Space<Node> {

	@Override
	public boolean feasible(Node x) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double feasibility(Node x) {
		return feasible(x) ? 1 : 0;
	}

	@Override
	public Node repair(Node x) {
		x.repair();
		return x;
	}

	@Override
	public Node pick() {
		// TODO Auto-generated method stub
		return new Node(null, RandomUtils.nextInt(0, 5));
	}

}
