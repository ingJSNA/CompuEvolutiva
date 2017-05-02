package co.edu.unal.funico.interpreter.fplearning;

/*
 * FunicoApp.java
 * -Xms512m -Xmx1024m
 * -Xms32m -Xmx32m
 */
import co.edu.unal.funico.interpreter.fplearning.interpreter.Evaluator;
import co.edu.unal.funico.interpreter.fplearning.interpreter.GoalException;
import co.edu.unal.funico.interpreter.fplearning.interpreter.ProgramException;
import co.edu.unal.funico.interpreter.fplearning.language.LexicalException;
import co.edu.unal.funico.interpreter.fplearning.language.SyntacticalException;

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
            System.out.println(Evaluator.evalue(
                    "mod3(0) = 0; mod3(1) = 1; mod3(2) = 2; mod3(s(s(s(X)))) = mod3(X)",
                    "mod3(5)"));
            System.out.println(Evaluator.evalue(
                    "even(0) = true; even(1) = false; even(s(s(X))) = even(X)",
                    "even(5)"));
            System.out.println(Evaluator.evalue(
                    "sum(0,X) = X; sum(s(X),Y) = s(sum(X,Y))",
                    "sum(5,3)"));
            
            String[] functor = {"geq", "s"};
            int[] arityFun = {2, 1};
            String[] terminal = {"0", "X", "Y"};
        } catch (ProgramException | GoalException | LexicalException | SyntacticalException ex) {
            System.out.println(ex);
        }
    }
}