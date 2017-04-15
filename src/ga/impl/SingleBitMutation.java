/**
 * 
 */
package ga.impl;

import org.apache.commons.lang3.RandomUtils;

import ga.Individual;
import ga.MutationMethod;

/**
 * @author juan
 *
 */
public class SingleBitMutation implements MutationMethod {

	@Override
	public Individual mutate(Individual individual) {
		int mutationIndex = RandomUtils.nextInt(0, individual.getArray().size());
		individual.getArray().flip(mutationIndex);
		return individual;
	}

}
