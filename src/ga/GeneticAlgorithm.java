package ga;

import java.util.List;

public class GeneticAlgorithm {

	private long populationSize;
	private SelectionMethod selection;
	private MutationMethod mutation;
	private CrossoverMethod crossover;

	/**
	 * @param populationSize
	 * @param selection
	 * @param mutation
	 * @param crossover
	 */
	public GeneticAlgorithm(long populationSize, SelectionMethod selection,
			MutationMethod mutation, CrossoverMethod crossover) {
		super();
		this.populationSize = populationSize;
		this.selection = selection;
		this.mutation = mutation;
		this.crossover = crossover;
	}

	public Individual solve(FitnessFunction fitness) {
		Population population = initpop(this.populationSize);
		long i = 0;
		while (!termination(population, i)) {
			population = next(population, fitness);
			i++;
		}
		return best(population);
	}

	private Population next(Population population, FitnessFunction fitness) {
		// TODO Auto-generated method stub
		return null;
	}

	private Individual best(Population population) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean termination(Population population, long i) {
		// TODO Auto-generated method stub
		return false;
	}

	private Population initpop(long populationSize2) {
		// TODO Auto-generated method stub
		return null;
	}

}
