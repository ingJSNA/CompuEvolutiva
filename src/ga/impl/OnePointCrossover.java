package ga.impl;

import org.apache.commons.lang3.RandomUtils;

import ga.CrossoverMethod;
import ga.Individual;

public class OnePointCrossover implements CrossoverMethod {

	@Override
	public Individual[] cross(Individual parentA, Individual parentB) {

		Individual[] offspring = new Individual[2];
		Individual child1 = new Individual();
		Individual child2 = new Individual();

		// ignore 0 and length as cross points
		int length = parentA.getArray().size();
		final int crossoverIndex = RandomUtils.nextInt(1, length - 1);

		// copy the first part
		for (int i = 0; i < crossoverIndex; i++) {
			child1.getArray().set(i, parentA.getArray().get(i));
			child2.getArray().set(i, parentB.getArray().get(i));
		}
		// and switch the second part
		for (int i = crossoverIndex; i < length; i++) {
			child1.getArray().set(i, parentB.getArray().get(i));
			child2.getArray().set(i, parentA.getArray().get(i));
		}

		offspring[0] = child1;
		offspring[1] = child2;

		return offspring;
	}

}
