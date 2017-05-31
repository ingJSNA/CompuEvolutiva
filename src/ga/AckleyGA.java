package ga;

import gp.funico.FunicoGP;

import java.util.concurrent.Callable;

import org.apache.commons.validator.GenericValidator;

import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.io.Write;
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
import unalcol.search.population.Population;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.population.PopulationSearch;
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

public class AckleyGA implements Callable<Double> {

	private static final int DIM = 24;
	private static final int POPSIZE = 100;
	private static int MAXITERS = 1_000;
	private boolean traceSearch;
	private String traceFileName;

	public AckleyGA() {

	}

	private double evolve() {
		// Search Space definition
		int DIM = 10;
		double[] min = DoubleArray.create(DIM, -5.12);
		double[] max = DoubleArray.create(DIM, 5.12);

		Space<double[]> space = new HyperCube(min, max);

		// Optimization Function
		OptimizationFunction<double[]> function = new Rastrigin();
		Goal<double[], Double> goal = new OptimizationGoal<double[]>(function);

		// Variation definition
		DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to
																			// Gaussian or other
																			// symmetric number
																			// generator (centered
																			// in zero)
		PickComponents pick = new PermutationPick(DIM / 2); // It can be set to null if the mutation
															// operator is applied to every
															// component of the solution array
		// AdaptMutationIntensity adapt = new OneFifthRule(500, 0.9); // It can be set to null if no
		// mutation adaptation is required
		IntensityMutation mutation = new IntensityMutation(0.1, random, pick);
		RealArityTwo xover = new LinearXOver();

		// Search method

		EAFactory<double[]> factory = new EAFactory<double[]>();
		PopulationSearch<double[], Double> search = factory.steady_ga(POPSIZE,
				new Tournament<double[]>(4), mutation, xover, 0.6, MAXITERS);

		// Tracking the goal evaluations
		WriteDescriptors write_desc = new WriteDescriptors();
		Write.set(double[].class, new DoubleArrayPlainWrite(false));
		Write.set(Population.class, write_desc);
		Descriptors.set(Population.class, new PopulationDescriptors<double[]>());

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
		Solution<double[]> solution = search.solve(space, goal);

		double goalSolution = (double) solution.info(Goal.class.getName());
		System.out.println(goalSolution);
		return goalSolution;
	}

	@Override
	public Double call() throws Exception {
		return evolve();
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
