package gp.run;

enum FunicoFile {
	then("then-example.txt", 0.25),
	geq("geq-example.txt", 0.66);

	private final String FOLDER = "./data/Funico/";

	private String fileName;
	private double bestFitness;

	/**
	 * @param fileName
	 * @param bestFitness
	 */
	private FunicoFile(String fileName, double bestFitness) {
		this.fileName = fileName;
		this.bestFitness = bestFitness;
	}

	/**
	 * @return the fileName
	 */
	public String getFilePath() {
		return FOLDER + fileName;
	}

	/**
	 * @return the bestFitness
	 */
	public double getBestFitness() {
		return bestFitness;
	}

}