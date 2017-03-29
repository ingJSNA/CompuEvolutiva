package ga;

import java.util.BitSet;
import java.util.Random;

public class Population {

	private static final Random RANDOM = new Random();

	private BitSet array;

	/**
	 * Initialize the population
	 * 
	 * @param populationSize2
	 * @return
	 */
	public static Population initpop(int populationSize2) {
		Population population = new Population();
		population.array = new BitSet(populationSize2);

		for (int i = 0; i < population.array.length(); i++) {
			population.array.set(i, RANDOM.nextBoolean());
		}

		return population;
	}

}
