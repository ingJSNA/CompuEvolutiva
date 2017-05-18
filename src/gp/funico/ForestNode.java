package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.funico.interpreter.funico.interpreter.Evaluator;
import co.edu.unal.funico.interpreter.funico.interpreter.GoalException;
import co.edu.unal.funico.interpreter.funico.interpreter.ProgramException;
import co.edu.unal.funico.interpreter.funico.language.LexicalException;
import co.edu.unal.funico.interpreter.funico.language.SyntacticalException;

public class ForestNode implements Cloneable {

	private static final Logger LOG = LoggerFactory.getLogger(ForestNode.class);

	private List<EquationNode> trees;

	public ForestNode(int maxEquations, int maxNodesByEquation, Expression expression) {

		trees = new ArrayList<EquationNode>();

		for (int i = RandomUtils.nextInt(0, maxEquations - 1); i < maxEquations; i++) {
			int hight = RandomUtils.nextInt(0, maxNodesByEquation);
			trees.add(new EquationNode(expression, null, hight));
		}
	}

	/**
	 * Copy constructor
	 * 
	 * @param forest
	 */
	public ForestNode(ForestNode forest) {

		trees = new ArrayList<EquationNode>(forest.trees.size());
		for (EquationNode equation : forest.trees) {
			trees.add(equation.clone(null));
		}
	}

	public double evaluate(String goal) {
		try {
			Evaluator.evalue(getSource(), goal);
			return 1;
		} catch (LexicalException | SyntacticalException | ProgramException | GoalException e) {
			LOG.error("Error al evaluar el programa. Forest Node: {}. Goal: {}", this, goal, e);
			throw new RuntimeException();
		}
	}

	/**
	 * Build the source of the program
	 * 
	 * @return
	 */
	private String getSource() {
		StringBuilder sb = new StringBuilder();
		for (EquationNode equation : trees) {
			sb.append(equation.getSource());
			sb.append(Expression.EQUATION_SEPARATOR);
		}
		return sb.toString().trim();
	}

	/**
	 * Number of equations
	 * 
	 * @return
	 */
	public int equationsCount() {
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
		// TODO

	}
}
