package ga;

public interface SelectionMethod {

	public Individual[] select(Individual[] population, FitnessFunction fitness);

}
