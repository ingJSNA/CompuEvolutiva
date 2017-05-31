package ga;

import gp.funico.FunicoGP;

import java.util.concurrent.Callable;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.evolution.ga.GAStep;
import unalcol.io.Write;
import unalcol.math.logic.Predicate;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.XOver;
import unalcol.optimization.binary.testbed.MaxOnes;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.optimization.real.xover.RealArityTwo;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.Population;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.population.PopulationReplacement;
import unalcol.search.population.PopulationSearch;
import unalcol.search.population.TotalSelectionReplacement;
import unalcol.search.selection.Roulette;
import unalcol.search.selection.Selection;
import unalcol.search.selection.Tournament;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.FileTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class AckleyGA {

	private static final Logger LOG = LoggerFactory.getLogger(AckleyGA.class);
	private final int DIM = 24;
	private final int POPSIZE = 100;
	private int MAXITERS = 1_000;
	private boolean traceSearch;
	private String traceFileName;

	public AckleyGA() {

	}

	public double evolve() {
		// Search Space definition
		Space<BitArray> space = new BinarySpace(DIM);

		// Optimization Function
		OptimizationFunction<BitArray> function = new RealFunctionFitness(DIM);

		// minimizing
		Goal<BitArray, Double> goal = new OptimizationGoal<BitArray>(function);

		// Variation definition
		Selection<BitArray> parent_selection = new Tournament<BitArray>(4);
		Variation_1_1<BitArray> mutation = new BitMutation(0.0);
		XOver xover = new XOver();
		double xover_probability = 1.0;

		// Search method

		EAFactory<BitArray> factory = new EAFactory<BitArray>();
		PopulationSearch<BitArray, Double> search = factory.generational_ga(POPSIZE,
				parent_selection, mutation, xover, xover_probability, MAXITERS);

		PopulationReplacement<BitArray> replace = new TotalSelectionReplacement<BitArray>(
				new Tournament<BitArray>(4));

		GAStep<BitArray> step = new GAStep<BitArray>(POPSIZE, parent_selection, mutation, xover,
				xover_probability, replace);

		// End the evolution after the max iterations
		Predicate<Population<BitArray>> tC = new ForLoopCondition<Population<BitArray>>(MAXITERS);

		search = new IterativePopulationSearch<BitArray, Double>(step, tC);

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
		Tracer.addTracer(goal, tracer);

		if (traceSearch) {
			Tracer.addTracer(search, tracer);
		}

		// Apply the search method
		Solution<BitArray> solution = search.solve(space, goal);

		LOG.warn("Solution: {}", solution.object());

		double solutionFitness = (double) solution.info(Goal.class.getName());
		LOG.warn("solutionFitness: {}", solutionFitness);

		return solutionFitness;
	}

	/**
	 * Set to true if you want to trace the algorithm
	 * 
	 * @param traceSearch
	 * @return
	 */
	public AckleyGA traceSearch(boolean traceSearch) {
		this.traceSearch = traceSearch;
		return this;
	}

	public AckleyGA setMaxIterations(int i) {
		this.MAXITERS = i;
		return this;
	}

	public AckleyGA setTraceFile(String fileName) {
		this.traceFileName = fileName;
		return this;
	}

}
