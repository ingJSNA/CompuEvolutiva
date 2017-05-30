package gp.run;

enum FunicoFile {
	then("then.txt", 0),
	double_f("double.txt", 0),
	even("even.txt", 0.34),
	iff("iff.txt", 0),
	mod3("mod3.txt", 0.34),
	max("max.txt", 0.225),
	sum("sum.txt", 1),
	min("min.txt", 0.2),
	geq("geq.txt", 0.34);

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