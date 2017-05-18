package gp.funico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.RandomCollectionsUtils;
import co.edu.unal.funico.interpreter.funico.interpreter.Evaluator;
import co.edu.unal.funico.interpreter.funico.interpreter.GoalException;
import co.edu.unal.funico.interpreter.funico.interpreter.ProgramException;
import co.edu.unal.funico.interpreter.funico.language.LexicalException;
import co.edu.unal.funico.interpreter.funico.language.SyntacticalException;

public class ForestNode implements Cloneable {

	private static final Logger LOG = LoggerFactory.getLogger(ForestNode.class);

	private List<EquationNode> trees;

	private Expression expression;

	public ForestNode(int maxEquations, int maxNodesByEquation, Expression expression) {
		this.expression = expression;

		trees = new ArrayList<EquationNode>();

		for (int i = RandomUtils.nextInt(0, maxEquations - 1); i < maxEquations; i++) {
			int hight = RandomUtils.nextInt(0, maxNodesByEquation);
			trees.add(new EquationNode(expression, null, hight));
			trees.add(new EquationNode(expression, null, hight));
		}

		this.repair();
	}

	/**
	 * Copy constructor
	 * 
	 * @param forest
	 */
	public ForestNode(ForestNode forest) {
		this.expression = forest.expression;
		trees = new ArrayList<EquationNode>(forest.trees.size());
		for (EquationNode equation : forest.trees) {
			trees.add(equation.clone(null));
		}
	}

	public double evaluate(String goal) {
		try {
			Evaluator.evalue(getSource(), goal);
			return 1;
		} catch (GoalException e) {
			LOG.debug("No se cumple el ejemplo. Forest Node: [{}]. Goal: [{}]", this, goal, e);
			return 2;
		} catch (SyntacticalException e) {
			LOG.warn("SyntacticalException. Forest Node: [{}]. Goal: [{}]", this, goal, e);
			return 4;
		} catch (LexicalException | ProgramException e) {
			LOG.error("Error al evaluar el programa. Forest Node: [{}]. Goal: [{}]", this, goal, e);
			throw new RuntimeException(e);
		}
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

	public EquationNode getEquation(int index) {
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
				String oper = RandomCollectionsUtils.getItem(expression.getFunctions());
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
						equation.oper = RandomCollectionsUtils.getItem(expression.getTerminals());
					}
					// All variables in the right side must be in the left side
					else if (!leftVariables.contains(equation.oper)) {
						equation.oper = RandomCollectionsUtils.getItem(leftVariables);
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
	 * @param tree
	 */
	public void setTree(int index, EquationNode tree) {
		trees.remove(index);
		trees.add(index, tree);
		tree.parent = null;

	}
}
