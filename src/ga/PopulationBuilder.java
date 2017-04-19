package ga;

import java.util.BitSet;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;

public class PopulationBuilder {

	private PopulationBuilder() {
	}

	/**
	 * Initialize the population
	 * 
	 * @param populationSize2
	 * @param individualDimensions
	 * @return
	 */
	public static Individual[] initpop(int populationSize2, int dimensions) {
		Individual[] array = new Individual[populationSize2];

		for (int i = 0; i < array.length; i++) {
			array[i] = getNewIndividual(dimensions);
		}

		return array;
	}

	private static Individual getNewIndividual(int dimensions) {
		Individual individual = new Individual();
		BitSet bitset = new BitSet(dimensions);
		for (int i = 0; i < dimensions; i++) {
			bitset.set(i, RandomUtils.nextBoolean());
		}
		individual.setArray(bitset);
		return individual;
	}
}
