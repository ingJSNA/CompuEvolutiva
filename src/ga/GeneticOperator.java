package ga;

public interface GeneticOperator {

	public Individual[] operate(Individual[] parents, FitnessFunction fitness);

}
