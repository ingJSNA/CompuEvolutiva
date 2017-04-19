package ga;

public class GeneticAlgorithm {

	private static final int NUMBER_OF_PARENTS = 2;
	private static final int DIMENSIONS = 9;
	private static final long ITERATIONS = 100;

	private int populationSize;
	private SelectionMethod selectionMethod;
	private GeneticOperator geneticOperator;

	private ReplaceMethod replaceMehod;

	/**
	 * @param populationSize
	 * @param selection
	 * @param mutation
	 * @param crossover
	 */
	public GeneticAlgorithm(int populationSize, SelectionMethod selection,
			GeneticOperator geneticOperator, ReplaceMethod replaceMehod) {
		super();
		this.populationSize = populationSize;
		this.selectionMethod = selection;
		this.geneticOperator = geneticOperator;
		this.replaceMehod = replaceMehod;
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

	/**
	 * Generate the next generation.
	 * 
	 * @param population
	 * @param fitness
	 * @return
	 */
	private Individual[] next(Individual[] population, FitnessFunction fitness) {
		Individual[] parents = selectionMethod.select(NUMBER_OF_PARENTS, population, fitness);
		Individual[] offspring1 = geneticOperator.operate(parents, fitness);
		Individual[] offspring = replaceMehod.replace(population, offspring1, fitness);
		return offspring;
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
		return i > GeneticAlgorithm.ITERATIONS;
	}

	/**
	 * Initialize the population with the given size
	 * 
	 * @param populationSize2
	 * @return
	 */
	private Individual[] initpop(int populationSize) {

		return PopulationBuilder.initpop(populationSize, DIMENSIONS);
	}

}
