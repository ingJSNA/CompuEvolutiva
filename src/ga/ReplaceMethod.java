package ga;

public interface ReplaceMethod {

	public Individual[] replace(Individual[] population,
			Individual[] offspring1, FitnessFunction fitness);

}
