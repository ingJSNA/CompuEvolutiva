package ga;

public interface SelectionMethod {

	/**
	 * Select n individuals from the population. Optionally use the fitness function.
	 * 
	 * @param n
	 * @param population
	 * @param fitness
	 * @return
	 */
	public Individual[] select(int n, Individual[] population, FitnessFunction fitness);

}
