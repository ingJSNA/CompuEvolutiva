package ga.commons;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.SelectionPolicy;
import org.apache.commons.math3.genetics.StoppingCondition;

public class StatisticGeneticAlgorithm extends GeneticAlgorithm {

	private int generationsEvolved;

	public StatisticGeneticAlgorithm(CrossoverPolicy crossoverPolicy, double crossoverRate,
			MutationPolicy mutationPolicy, double mutationRate, SelectionPolicy selectionPolicy)
			throws OutOfRangeException {
		super(crossoverPolicy, crossoverRate, mutationPolicy, mutationRate, selectionPolicy);
	}

	@Override
	public Population evolve(Population initial, StoppingCondition condition) {
		Population current = initial;
		generationsEvolved = 0;
		while (!condition.isSatisfied(current)) {
			current = nextGeneration(current);
			generationsEvolved++;
			System.out.println(current.getFittestChromosome().getFitness());
		}
		return current;
	}

	@Override
	public int getGenerationsEvolved() {
		return this.generationsEvolved;
	}

}
