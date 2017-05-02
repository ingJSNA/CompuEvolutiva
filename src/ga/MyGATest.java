package ga;

import java.util.concurrent.Callable;

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
import unalcol.search.Goal;
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
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class MyGATest implements Callable<Double> {

	private static final int DIM = 24;
	private static final int POPSIZE = 100;
	private static final int MAXITERS = 1_000;

	private static Double binary() {
		// Search Space definition

		Space<BitArray> space = new BinarySpace(DIM);

		// Optimization Function
		OptimizationFunction<BitArray> function = new MaxOnes();

		// maximizing, remove the parameter false if minimizing
		Goal<BitArray, Double> goal = new OptimizationGoal<BitArray>(function, false);

		// Variation definition
		Selection<BitArray> parent_selection = new Tournament<BitArray>(4);
		Variation_1_1<BitArray> mutation = new BitMutation();
		XOver xover = new XOver();
		double xover_probability = 1.0;

		// Search method

		EAFactory<BitArray> factory = new EAFactory<BitArray>();
		PopulationSearch<BitArray, Double> search = factory.generational_ga(POPSIZE,
				parent_selection, mutation, xover, xover_probability, MAXITERS);

		// Tracking the goal evaluations
		WriteDescriptors write_desc = new WriteDescriptors();
		Write.set(double[].class, new DoubleArrayPlainWrite(false));
		Write.set(Population.class, write_desc);
		Descriptors.set(Population.class, new PopulationDescriptors<BitArray>());

		Tracer tracer = new FileTracer("log/trace.txt");
		tracer = new ConsoleTracer();
		// Uncomment if you want to trace the function evaluations
		// Tracer.addTracer(goal, tracer);

		// Tracer.addTracer(search, tracer); // Uncomment if you want to trace the
		// hill-climbing algorithm

		// Apply the search method
		Solution<BitArray> solution = search.solve(space, goal);

		tracer.close();

		System.out.println(solution.info(Goal.class.getName()));
		System.out.println(solution.object());
		return (Double) solution.info(Goal.class.getName());
	}

	public static void main(String[] args) {
		try {
			System.out.println(new MyGATest().call());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Double call() throws Exception {
		return binary();
	}

}
