package gp.calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.validator.GenericValidator;

public class Expression {

	private String[] variables = new String[] { "X", "Y" };
	private String[] functions = new String[] { "*", "+" };
	private String[] terminals;

	public Expression() {
		List<String> list = new ArrayList<String>(19);
		for (int i = -9; i <= 9; i++) {
			list.add(String.valueOf(i));
		}
		terminals = list.toArray(new String[list.size()]);
	}

	public String[] getVariblesAndTerminals() {
		return ArrayUtils.addAll(variables, terminals);
	}

	public String[] getFunctions() {
		return ArrayUtils.addAll(functions);
	}

	public String[] getAll() {
		return ArrayUtils.addAll(getFunctions(), getVariblesAndTerminals());
	}

	public boolean isFunction(String oper) {
		return ArrayUtils.contains(getFunctions(), oper);
	}

	public boolean isTerminal(String oper) {
		return ArrayUtils.contains(terminals, oper);
	}

	public boolean isVariable(String oper) {
		return ArrayUtils.contains(variables, oper);
	}

}
