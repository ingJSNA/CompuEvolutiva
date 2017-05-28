package gp.run;

enum FunicoFile {
	then("then.txt", 0.2),
	geq("geq.txt", 0.34),
	double_f("double.txt", 1),
	even("even.txt", 1),
	iff("iff.txt", 1),
	max("max.txt", 1),
	min("min.txt", 1),
	mod3("mod3.txt", 1),
	sum("sum.txt", 1);

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