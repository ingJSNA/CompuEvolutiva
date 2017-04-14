/**
 * 
 */
package ga.impl;

import ga.FitnessFunction;
import ga.Individual;
import ga.SelectionMethod;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author juan
 *
 */
public class UniformSelection implements SelectionMethod {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ga.SelectionMethod#select(ga.Individual[], ga.FitnessFunction)
	 */
	@Override
	public Individual[] select(int n, Individual[] population, FitnessFunction fitness) {
		Individual[] parents = new Individual[n];
		for (int i = 0; i < n; i++) {
			int p = RandomUtils.nextInt(0, population.length);
			parents[i] = population[p];
		}
		return parents;
	}
}
