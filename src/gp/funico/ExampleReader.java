package gp.funico;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import co.edu.unal.funico.interpreter.funico.interpreter.ExampleException;
import co.edu.unal.funico.interpreter.funico.interpreter.Extractor;
import co.edu.unal.funico.interpreter.funico.language.LexicalException;
import co.edu.unal.funico.interpreter.funico.language.SyntacticalException;

public class ExampleReader {

	private int maxEquations;
	private int maxNodesByEquation;
	private Extractor extractor;
	private List<String> examples;

	public ExampleReader(File example) {
		loadExampleFile(example);
	}

	private void loadExampleFile(File example) {
		Validate.isTrue(example.exists());
		try {
			List<String> lines = FileUtils.readLines(example, StandardCharsets.UTF_8);
			this.maxEquations = Integer.parseInt(lines.remove(0));
			this.maxNodesByEquation = Integer.parseInt(lines.remove(0));
			this.examples = lines;
			extractor = new Extractor(StringUtils.join(lines, ";"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExampleException | LexicalException | SyntacticalException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

	/**
	 * @return the maxEquations
	 */
	public int getMaxEquations() {
		return maxEquations;
	}

	/**
	 * @return the maxNodesByEquation
	 */
	public int getMaxNodesByEquation() {
		return maxNodesByEquation;
	}

	/**
	 * @return the extractor
	 */
	public Extractor getExtractor() {
		return extractor;
	}

	/**
	 * @return the examples
	 */
	public List<String> getExamples() {
		return examples;
	}

}
