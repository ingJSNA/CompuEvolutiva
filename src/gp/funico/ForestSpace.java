package gp.funico;

import unalcol.search.space.Space;

public class ForestSpace extends Space<ForestNode> {

	private Expression expression;
	private int maxEquations;
	private int maxNodesByEquation;

	/**
	 * @param expression
	 * @param maxEquations
	 * @param maxNodesByEquation
	 */
	public ForestSpace(Expression expression, int maxEquations, int maxNodesByEquation) {
		super();
		this.expression = expression;
		this.maxEquations = maxEquations;
		this.maxNodesByEquation = maxNodesByEquation;
	}

	@Override
	public boolean feasible(ForestNode x) {
		// TODO
		return true;
	}

	@Override
	public double feasibility(ForestNode x) {
		return feasible(x) ? 1 : 0;
	}

	@Override
	public ForestNode repair(ForestNode x) {
		x.repair();
		return x;
	}

	@Override
	public ForestNode pick() {
		return new ForestNode(maxEquations, maxNodesByEquation, expression);
	}

}
