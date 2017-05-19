package gp.funico;

import unalcol.search.space.Space;

public class ForestSpace extends Space<Forest> {

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
	public boolean feasible(Forest x) {
		try {
			x.evaluate("");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public double feasibility(Forest x) {
		return feasible(x) ? 1 : 0;
	}

	@Override
	public Forest repair(Forest x) {
		x.repair();
		return x;
	}

	@Override
	public Forest pick() {
		return new Forest(maxEquations, maxNodesByEquation, expression);
	}

}
