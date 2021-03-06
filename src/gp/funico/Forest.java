package gp.funico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.RandomCollectionsUtils;
import co.edu.unal.funico.interpreter.funico.interpreter.Evaluator;
import co.edu.unal.funico.interpreter.funico.interpreter.GoalException;
import co.edu.unal.funico.interpreter.funico.language.SyntacticalException;

public class Forest implements Cloneable {

	private static final Logger LOG = LoggerFactory.getLogger(Forest.class);

	private List<EquationNode> trees;
	private Map<String, Double> fitnessCache;

	private Expression expression;

	private int maxNodesByEquation;

	private int maxEquations;

	private Forest() {
		fitnessCache = new HashMap<String, Double>();
	}

	public Forest(int maxEquations, int maxNodesByEquation,
			Expression expression) {
		this();
		this.expression = expression;
		this.maxNodesByEquation = maxNodesByEquation;
		this.maxEquations = maxEquations;

		trees = new ArrayList<EquationNode>();

		for (int i = RandomUtils.nextInt(0, maxEquations - 1); i < maxEquations; i++) {
			addEquation();
		}

		this.repair();
	}

	/**
	 * Copy constructor
	 * 
	 * @param forest
	 */
	public Forest(Forest forest) {
		this();
		this.expression = forest.expression;
		this.maxNodesByEquation = forest.maxNodesByEquation;
		this.maxEquations = forest.maxEquations;

		trees = new ArrayList<EquationNode>(forest.trees.size());
		for (EquationNode equation : forest.trees) {
			trees.add(equation.clone(null));
		}
	}

	/**
	 * Evaluate the program for the given goal
	 * 
	 * @param goal
	 * @return 0 if accomplish the goal, 1 if it doesn't
	 */
	public double evaluate(String goal) {
		Double fitness = Double.MAX_VALUE;
		try {
			// Check if the fitness for the goal is already calculated
			if (fitnessCache.containsKey(goal)) {
				fitness = fitnessCache.get(goal);
			} else {
				String[] sides = StringUtils.split(goal, "=");

				String value = Evaluator.evalue(getSource(), sides[0]);
				if (value.equals(sides[1].trim())) {
					fitness = 0D;
				} else {
					fitness = 1D;
				}
			}
		} catch (GoalException e) {
			LOG.error("Problema con el ejemplo. Forest Node: [{}]. Goal: [{}]",
					this, goal, e);
			throw new RuntimeException(e);
		} catch (SyntacticalException e) {
			LOG.debug("SyntacticalException. Forest Node: [{}]. Goal: [{}]",
					this, goal, e);
			fitness = 1_000D;
		} catch (Exception | StackOverflowError e) {
			LOG.error(
					"Error al evaluar el programa. Forest Node: [{}]. Goal: [{}]",
					this, goal, e);
			throw new RuntimeException(e);
		}
		// Save fitness
		fitnessCache.put(goal, fitness);

		return fitness;
	}

	/**
	 * Build the source of the program
	 * 
	 * @return
	 */
	private String getSource() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < trees.size(); i += 2) {
			EquationNode left = trees.get(i);
			EquationNode right = trees.get(i + 1);
			sb.append(left.getSource());
			sb.append(Expression.EQUATION_RIGHT_SEPARATOR);
			sb.append(right.getSource());
			sb.append(Expression.EQUATION_SEPARATOR);
		}
		return sb.toString().trim();
	}

	/**
	 * Number of equations
	 * 
	 * @return
	 */
	public int treesCount() {
		return trees.size();
	}

	public EquationNode getTree(int index) {
		return trees.get(index);
	}

	@Override
	public String toString() {
		return getSource();
	}

	/**
	 * Repair this object
	 */
	public void repair() {

		for (int i = 0; i < trees.size(); i += 2) {
			EquationNode left = trees.get(i);
			EquationNode right = trees.get(i + 1);

			Set<String> leftVariables = new HashSet<String>();
			Set<String> leftFunctions = new HashSet<String>();

			// Left side
			for (int e = 0; e < left.weight(); e++) {
				EquationNode equation = left.get(e);

				if (expression.getVaribles().contains(equation.oper)) {
					leftVariables.add(equation.oper);
				} else if (expression.getFunctions().contains(equation.oper)) {
					leftFunctions.add(equation.oper);
				}
			}

			// Must be at least one function on the left side
			if (leftFunctions.isEmpty()) {
				String oper = RandomCollectionsUtils.getItem(expression
						.getFunctions());
				EquationNode newRoot = new EquationNode(expression, null, oper);
				newRoot.children.add(left);
				left.parent = newRoot;
				this.setTree(i, newRoot);
				left = newRoot;
			}

			// Right side
			for (int e = 0; e < right.weight(); e++) {
				EquationNode equation = right.get(e);
				if (expression.getVaribles().contains(equation.oper)) {
					if (leftVariables.isEmpty()) {
						equation.oper = RandomCollectionsUtils
								.getItem(expression.getTerminals());
					}
					// All variables in the right side must be in the left side
					else if (!leftVariables.contains(equation.oper)) {
						equation.oper = RandomCollectionsUtils
								.getItem(leftVariables);
					}
				}
			}
			left.repair();
			right.repair();
		}

	}

	/**
	 * Set the tree in the given index, removing the current one.
	 * 
	 * @param index
	 * @param node
	 */
	public void setTree(int index, EquationNode node) {
		trees.remove(index);
		trees.add(index, node);
		node.parent = null;

	}

	/**
	 * Add randomly an equation to the forest;
	 */
	public void addEquation() {
		int hight = RandomUtils.nextInt(0, maxNodesByEquation / 2);
		trees.add(new EquationNode(expression, null, hight));
		hight = RandomUtils.nextInt(0, maxNodesByEquation / 2);
		trees.add(new EquationNode(expression, null, hight));

	}

	/**
	 * Remove randomly an equation from the forest;
	 */
	public void removeEquation() {
		if (this.equationCount() > 2) {
			int index = RandomUtils.nextInt(0, trees.size() / 2);
			trees.remove(index);
			trees.remove(index);
		}
	}

	public int equationCount() {
		return this.treesCount() / 2;
	}

	public int maxEquationTerms() {
		int max = 0;
		for (int i = 0; i < trees.size(); i += 2) {
			EquationNode left = trees.get(i);
			EquationNode right = trees.get(i + 1);
			max = Math.max(max, left.weight() + 1 + right.weight());
		}
		return max;
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @return the maxNodesByEquation
	 */
	public int getMaxNodesByEquation() {
		return maxNodesByEquation;
	}

	/**
	 * @return the maxEquations
	 */
	public int getMaxEquations() {
		return maxEquations;
	}
}
