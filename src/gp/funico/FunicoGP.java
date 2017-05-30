package gp.funico;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gp.GeneticProgramming;
import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.search.Goal;
import unalcol.search.population.Population;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Selection;
import unalcol.search.selection.Tournament;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.search.variation.Variation_2_2;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.FileTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class FunicoGP extends GeneticProgramming {

	private static final Logger LOG = LoggerFactory.getLogger(FunicoGP.class);
	private ExampleReader reader;
	private boolean traceSearch = false;
	private String traceFileName;

	public static FunicoGP getInstance(ExampleReader reader) {
		FunicoGP funicoGP = new FunicoGP();
		funicoGP.reader = reader;
		return funicoGP;
	}

	private FunicoGP() {
		super();
		super.MAXITERS = 200;
	}

	@Override
	public double evolve() {
		// Search Space definition
		Space<Forest> space = new ForestSpace(new Expression(reader.getExtractor()),
				reader.getMaxEquations(), reader.getMaxNodesByEquation());

		// Optimization Function
		OptimizationFunction<Forest> function = new ForestFitness(reader);

		// maximizing, remove the parameter false if minimizing
		Goal<Forest, Double> goal = new OptimizationGoal<Forest>(function, true);

		// Variation definition
		Selection<Forest> parent_selection = new Tournament<Forest>(4);
		Variation_1_1<Forest> mutation = new ForestMutation();
		Variation_2_2<Forest> xover = new ForestCrossover();
		double xover_probability = 1.0;

		// Search method
		EAFactory<Forest> factory = new EAFactory<Forest>();
		PopulationSearch<Forest, Double> search = factory.generational_ga(POPSIZE,
				parent_selection, mutation, xover, xover_probability, MAXITERS);

		search = factory.steady_ga(POPSIZE, parent_selection, mutation, xover, xover_probability,
				MAXITERS);

		// Tracking the goal evaluations
		WriteDescriptors write_desc = new WriteDescriptors();
		Write.set(double[].class, new DoubleArrayPlainWrite(false));
		Write.set(Population.class, write_desc);
		Descriptors.set(Population.class, new PopulationDescriptors<BitArray>());

		Tracer tracer;
		if (GenericValidator.isBlankOrNull(traceFileName)) {
			tracer = new ConsoleTracer();
		} else {
			tracer = new FileTracer(this.traceFileName);
		}

		// Uncomment if you want to trace the function evaluations
		// Tracer.addTracer(goal, tracer);

		if (traceSearch) {
			Tracer.addTracer(search, tracer);
		}

		// Apply the search method
		Solution<Forest> solution = search.solve(space, goal);

		tracer.close();

		Double solutionValue = (Double) solution.info(Goal.class.getName());
		LOG.warn("solutionValue: {}", solutionValue);
		LOG.warn("Solution: {}", solution.object());

		return solutionValue;
	}

	/**
	 * Set to true if you want to trace the algorithm
	 * 
	 * @param traceSearch
	 * @return
	 */
	public FunicoGP traceSearch(boolean traceSearch) {
		this.traceSearch = traceSearch;
		return this;
	}

	public FunicoGP setMaxIterations(int i) {
		this.MAXITERS = i;
		return this;
	}

	public FunicoGP setTraceFile(String fileName) {
		this.traceFileName = fileName;
		return this;
	}
}
