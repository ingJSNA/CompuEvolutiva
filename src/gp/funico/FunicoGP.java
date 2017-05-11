package gp.funico;

import gp.Node;
import gp.NodeCrossover;
import gp.NodeFitness;
import gp.NodeMutation;
import gp.NodeSpace;

import java.util.concurrent.Callable;

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

public class FunicoGP implements Callable<Double> {

	private static final int POPSIZE = 100;
	private static final int MAXITERS = 1_000;

	private static Double evolve() {
		// Search Space definition

		Space<Node> space = new NodeSpace();

		// Optimization Function
		OptimizationFunction<Node> function = new NodeFitness(null);

		// maximizing, remove the parameter false if minimizing
		Goal<Node, Double> goal = new OptimizationGoal<Node>(function, false);

		// Variation definition
		Selection<Node> parent_selection = new Tournament<Node>(4);
		Variation_1_1<Node> mutation = new NodeMutation();
		Variation_2_2<Node> xover = new NodeCrossover();
		double xover_probability = 1.0;

		// Search method

		EAFactory<Node> factory = new EAFactory<Node>();
		PopulationSearch<Node, Double> search = factory.generational_ga(POPSIZE, parent_selection,
				mutation, xover, xover_probability, MAXITERS);

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
		Solution<Node> solution = search.solve(space, goal);

		tracer.close();

		System.out.println(solution.info(Goal.class.getName()));
		System.out.println(solution.object());
		return (Double) solution.info(Goal.class.getName());
	}

	@Override
	public Double call() throws Exception {
		return evolve();
	}
}