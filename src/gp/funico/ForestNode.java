package gp.funico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.validator.GenericValidator;
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
			trees.add(new EquationNode(expression, null, RandomUtils.nextInt(0, maxNodesByEquation)));
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
			trees.add(new EquationNode(equation));
		}
	}

	public double evaluate(String goal) {
		try {
			Evaluator.evalue(getSource(), goal);
			return 1;
		} catch (LexicalException | SyntacticalException | ProgramException | GoalException e) {
			LOG.error("Error al evaluar el programa. Goal: {}", goal, e);
		}
		return 0;
	}

	private String getSource() {
		// TODO Auto-generated method stub
		return "";
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
		String string = super.toString();
		return string;
	}

	/**
	 * Repair this object
	 */
	public void repair() {
		// TODO

	}
}
