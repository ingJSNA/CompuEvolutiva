package gp;

public abstract class GeneticProgramming {

	protected int POPSIZE = 100;
	protected int MAXITERS = 500;

	public abstract double evolve();
}
