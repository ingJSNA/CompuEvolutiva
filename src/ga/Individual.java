package ga;

import java.util.BitSet;

public class Individual {

	private double fitness;
	private BitSet array;

	/**
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * @param fitness
	 *            the fitness to set
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * @return the array
	 */
	public BitSet getArray() {
		return array;
	}

	/**
	 * @param array
	 *            the array to set
	 */
	public void setArray(BitSet array) {
		this.array = array;
	}

}
