package ga.impl;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.lang3.ArrayUtils;

import ga.FitnessFunction;
import ga.Individual;
import ga.ReplaceMethod;

public class ElitismReplace implements ReplaceMethod {

	@Override
	public Individual[] replace(Individual[] population, Individual[] offspring1,
			FitnessFunction fitness) {
		Individual[] P = new Individual[population.length + offspring1.length];
		P = ArrayUtils.addAll(population, offspring1);
		Arrays.sort(P, new Comparator<Individual>() {

			@Override
			public int compare(Individual o1, Individual o2) {
				return Double.compare(o1.getFitness(), o2.getFitness());
			}
		});
		return Arrays.copyOf(P, population.length);
	}

}
