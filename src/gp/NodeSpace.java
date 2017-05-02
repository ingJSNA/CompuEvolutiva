package gp;

import unalcol.search.space.Space;

public class NodeSpace extends Space<Node> {

	@Override
	public boolean feasible(Node x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double feasibility(Node x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node repair(Node x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node pick() {
		// TODO Auto-generated method stub
		return null;
	}

}
