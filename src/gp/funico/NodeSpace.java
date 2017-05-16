package gp.funico;

import org.apache.commons.lang3.RandomUtils;

import unalcol.search.space.Space;

public class NodeSpace extends Space<EquationNode> {

	@Override
	public boolean feasible(EquationNode x) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double feasibility(EquationNode x) {
		return feasible(x) ? 1 : 0;
	}

	@Override
	public EquationNode repair(EquationNode x) {
		x.repair();
		return x;
	}

	@Override
	public EquationNode pick() {
		// TODO Auto-generated method stub
		return new EquationNode(null, RandomUtils.nextInt(0, 5));
	}

}
