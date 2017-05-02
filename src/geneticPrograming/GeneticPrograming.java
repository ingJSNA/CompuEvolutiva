package geneticPrograming;

import ga.Individual;

import java.util.ArrayList;
import java.util.List;

public class GeneticPrograming {

	private List<Node<String>> population;
	private String[] alphabet = new String[] { "*", "+", "x" };

	private void initializePopulation() {

	}

	public void evolve() {
		initializePopulation();

		List<Node<String>> offspring = new ArrayList<Node<String>>();
		for (int i = 0; i < population.size(); i += 2) {
		offspring.addAll(cross(population.get(i), population.get(i+1)));

	}

	private List<Node<String>> cross(Node<String> parentA, Node<String> parentB) {
		List<Node<String>> offspring = new ArrayList<Node<String>>(2);
		double r = 0.5;
		double Pxover = 1;
		if (r <= Pxover) {
			offspring = crossover.cross(parents[i], parents[i + 1]);
			offspring[i] = mutation.mutate(offspring[i]);
			offspring[i + 1] = mutation.mutate(offspring[i + 1]);
		} else {
			offspring[i] = parents[i];
			offspring[i + 1] = parents[i + 1];
		}

		return offspring;
	}

}
