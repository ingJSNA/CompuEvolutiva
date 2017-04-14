/**
 * 
 */
package ga.impl;

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

		return null;
	}

}
