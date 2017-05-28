package gp.run;

enum FunicoFile {
	then("then.txt", 0.2),
	geq("geq.txt", 0.34),
	double_f("double.txt", 1),
	even("double.txt", 1),
	iff("double.txt", 1),
	max("double.txt", 1),
	min("double.txt", 1),
	mod3("double.txt", 1),
	sum("double.txt", 1);

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