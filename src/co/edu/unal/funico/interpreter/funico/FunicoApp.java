package co.edu.unal.funico.interpreter.funico;

/*
 * FunicoApp.java
 * -Xms512m -Xmx1024m
 * -Xms32m -Xmx32m
 */

import co.edu.unal.funico.interpreter.funico.interpreter.Evaluator;
import co.edu.unal.funico.interpreter.funico.interpreter.ExampleException;
import co.edu.unal.funico.interpreter.funico.interpreter.Extractor;
import co.edu.unal.funico.interpreter.funico.interpreter.GoalException;
import co.edu.unal.funico.interpreter.funico.interpreter.ProgramException;
import co.edu.unal.funico.interpreter.funico.language.LexicalException;
import co.edu.unal.funico.interpreter.funico.language.SyntacticalException;

/**
 * The main class of the application.
 */
public class FunicoApp {

	public FunicoApp() {
	}

	/**
	 * Main method launching the application.
	 */
	public static void main(String[] args) {
		try {
			Extractor ext = new Extractor(
					"geq(0,1) = false; geq(0,0) = true; geq(1,0) = true; geq(1,1) = true; geq(1,2) = false; geq(2,1) = true; geq(2,5) = false; geq(5,2) = true; geq(3,3) = true");
			System.out.println(ext.getTableMainFunctors());
			System.out.println(ext.getTableFunctors());
			System.out.println(ext.getTableTerminals());
			System.out.println(ext.getTableVariables());
			System.out.println();
			System.out
					.println(Evaluator.evalue(
							"mod3(0) = 0; mod3(1) = 1; mod3(2) = 2; mod3(s(s(s(X)))) = mod3(X)",
							"mod3(5)"));
			System.out.println(Evaluator.evalue(
					"even(0) = true; even(1) = false; even(s(s(X))) = even(X)", "even(5)"));
			System.out.println(Evaluator.evalue("sum(0,X) = X; sum(s(X),Y) = s(sum(X,Y))",
					"sum(5,3)"));
			System.out.println();
			System.out.println(Evaluator.evalue("sum(0,X) = X; sum(s(X),Y) = s(sum(X,Y))",
					"sum(5,4) = 0"));
		} catch (ExampleException | ProgramException | GoalException | LexicalException
				| SyntacticalException ex) {
			System.out.println(ex);
		}
	}
}