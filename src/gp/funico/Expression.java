package gp.funico;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import co.edu.unal.funico.interpreter.funico.interpreter.Extractor;

public class Expression {

	public static final String EQUATION_SEPARATOR = "; ";

	public static final String EQUATION_RIGHT_SEPARATOR = "=";

	private static final Map<String, Integer> functionsFunico;

	static {
		functionsFunico = new HashMap<String, Integer>();
		functionsFunico.put("s", 1);
	}

	private final Set<String> variables;
	private final Set<String> terminals;
	private final Set<String> mainFunctors;
	private final Map<String, Integer> functors;
	private final Set<String> variablesAndTerminals;

	private Map<String, Integer> functions;

	public Expression(Extractor extractor) {
		this.mainFunctors = extractor.getTableMainFunctors();
		this.functors = extractor.getTableFunctors();
		this.terminals = extractor.getTableTerminals();
		this.variables = extractor.getTableVariables();

		variablesAndTerminals = new HashSet<String>(variables);
		variablesAndTerminals.addAll(terminals);

		this.functions = new HashMap<String, Integer>(functionsFunico);

		// Put each of the elements of functors in functions
		functors.forEach(functions::putIfAbsent);

	}

	public Set<String> getVariblesAndTerminals() {
		return variablesAndTerminals;
	}

	public Set<String> getVaribles() {
		return variables;
	}

	public Set<String> getFunctions() {
		return functions.keySet();
	}

	/**
	 * Return the arity for the given function.
	 * 
	 * @param function
	 * @return
	 */
	public int getFunctionArity(String function) {
		if (functions.containsKey(function)) {
			return functions.get(function);
		} else {
			return 0;
		}
	}

	/**
	 * @return the terminals
	 */
	public Set<String> getTerminals() {
		return terminals;
	}

}
