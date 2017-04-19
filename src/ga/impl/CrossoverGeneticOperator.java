/**
 * 
 */
package ga.impl;

import org.apache.commons.lang3.RandomUtils;

import ga.CrossoverMethod;
import ga.FitnessFunction;
import ga.GeneticOperator;
import ga.Individual;
import ga.MutationMethod;

/**
 * @author juan
 *
 */
public class CrossoverGeneticOperator implements GeneticOperator {

	private static final double Pxover = 0.9;

	private MutationMethod mutation;
	private CrossoverMethod crossover;

	public CrossoverGeneticOperator(CrossoverMethod crossover, MutationMethod mutation) {
		this.crossover = crossover;
		this.mutation = mutation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ga.GeneticOperator#operate(ga.Individual[], ga.FitnessFunction)
	 */
	@Override
	public Individual[] operate(Individual[] parents, FitnessFunction fitness) {
		Individual[] offspring = new Individual[parents.length];
		for (int i = 0; i < parents.length; i += 2) {
			double r = RandomUtils.nextDouble(0, 1);
			if (r <= Pxover) {
				offspring = crossover.cross(parents[i], parents[i + 1]);
				offspring[i] = mutation.mutate(offspring[i]);
				offspring[i + 1] = mutation.mutate(offspring[i + 1]);
			} else {
				offspring[i] = parents[i];
				offspring[i + 1] = parents[i + 1];
			}
		}
		return offspring;
	}
}
