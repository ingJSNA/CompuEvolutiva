package ga;

public class GeneticAlgorithm {

	private int populationSize;
	private SelectionMethod selection;
	private MutationMethod mutation;
	private CrossoverMethod crossover;
	private long maxIterations = 100;

	/**
	 * @param populationSize
	 * @param selection
	 * @param mutation
	 * @param crossover
	 */
	public GeneticAlgorithm(int populationSize, SelectionMethod selection,
			MutationMethod mutation, CrossoverMethod crossover) {
		super();
		this.populationSize = populationSize;
		this.selection = selection;
		this.mutation = mutation;
		this.crossover = crossover;
	}

	/**
	 * Find the best individual for the given fitness function
	 * 
	 * @param fitness
	 * @return
	 */
	public Individual solve(FitnessFunction fitness) {
		Individual[] population = initpop(this.populationSize);
		long i = 0;
		while (!termination(population, i)) {
			population = next(population, fitness);
			i++;
		}
		return best(population);
	}

	private Individual[] next(Individual[] population, FitnessFunction fitness) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find the individual with the lowest fitness
	 * 
	 * @param population
	 * @return
	 */
	private Individual best(Individual[] population) {
		Individual best = population[0];
		for (Individual individual : population) {
			if (individual.getFitness() < best.getFitness()) {
				best = individual;
			}
		}
		return best;
	}

	/**
	 * Determine if process must stop
	 * 
	 * @param population
	 * @param i
	 * @return
	 */
	private boolean termination(Individual[] population, long i) {
		return i > this.maxIterations;
	}

	/**
	 * Initialize the population with the given size
	 * 
	 * @param populationSize2
	 * @return
	 */
	private Individual[] initpop(int populationSize) {
		int individualDimensions = 9;
		return PopulationBuilder.initpop(populationSize, individualDimensions);
	}

}
