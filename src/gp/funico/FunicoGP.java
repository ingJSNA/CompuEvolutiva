package gp.funico;

import gp.GeneticProgramming;

import java.util.ArrayList;
import java.util.List;

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
import co.edu.unal.funico.interpreter.funico.interpreter.Extractor;

public class FunicoGP extends GeneticProgramming {

	private int maxEquations;
	private int maxNodesByEquation;
	private Extractor extractor;

	public static FunicoGP getInstance(int maxEquations, int maxNodesByEquation, Extractor extractor) {
		FunicoGP funicoGP = new FunicoGP();
		funicoGP.maxEquations = maxEquations;
		funicoGP.maxNodesByEquation = maxNodesByEquation;
		funicoGP.extractor = extractor;
		return funicoGP;
	}

	private FunicoGP() {

	}

	@Override
	public double evolve() {
		// Search Space definition

		Space<ForestNode> space = new ForestSpace(new Expression(extractor), maxEquations,
				maxNodesByEquation);

		// Optimization Function
		List<Example> examples = new ArrayList<Example>();
		examples.add(new Example(5, 4));
		examples.add(new Example(17, 4));
		examples.add(new Example(5, 21));
		examples.add(new Example(55, 4));
		examples.add(new Example(0, 0));
		examples.add(new Example(2, 0));
		examples.add(new Example(-2, 0));
		examples.add(new Example(-4, 1));
		examples.add(new Example(1, 1));
		examples.add(new Example(4, -1));

		OptimizationFunction<ForestNode> function = new ForestNodeFitness(examples);

		// maximizing, remove the parameter false if minimizing
		Goal<ForestNode, Double> goal = new OptimizationGoal<ForestNode>(function, true);

		// Variation definition
		Selection<ForestNode> parent_selection = new Tournament<ForestNode>(4);
		Variation_1_1<ForestNode> mutation = new ForestNodeMutation();
		Variation_2_2<ForestNode> xover = new ForestNodeCrossover();
		double xover_probability = 1.0;

		// Search method

		EAFactory<ForestNode> factory = new EAFactory<ForestNode>();
		PopulationSearch<ForestNode, Double> search = factory.generational_ga(POPSIZE,
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

		Tracer.addTracer(search, tracer); // Uncomment if you want to trace the
		// hill-climbing algorithm

		// Apply the search method
		Solution<ForestNode> solution = search.solve(space, goal);

		tracer.close();

		System.out.println(solution.info(Goal.class.getName()));
		System.out.println(solution.object());
		return (Double) solution.info(Goal.class.getName());
	}
}
