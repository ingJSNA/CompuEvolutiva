package gp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

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

}
