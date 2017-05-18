package gp.funico;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import co.edu.unal.funico.interpreter.funico.interpreter.Extractor;

public class Expression {

	public static final String EQUATION_SEPARATOR = "; ";

	public static final String EQUATION_RIGHT_SEPARATOR = " = ";

	private final Set<String> variables;
	private final Set<String> terminals;
	private final Set<String> mainFunctors;
	private final Map<String, Integer> functors;
	private final Set<String> variablesAndTerminals;

	public Expression(Extractor extractor) {
		this.mainFunctors = extractor.getTableMainFunctors();
		this.functors = extractor.getTableFunctors();
		this.terminals = extractor.getTableTerminals();
		this.variables = extractor.getTableVariables();

		variablesAndTerminals = new HashSet<String>(variables);
		variablesAndTerminals.addAll(terminals);

	}

	public Set<String> getVariblesAndTerminals() {
		return variablesAndTerminals;
	}

	public Set<String> getVaribles() {
		return variables;
	}

	public Set<String> getFunctions() {
		return functors.keySet();
	}

	/**
	 * @return the terminals
	 */
	public Set<String> getTerminals() {
		return terminals;
	}

}
