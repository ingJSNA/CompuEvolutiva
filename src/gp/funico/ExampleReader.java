package gp.funico;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.funico.interpreter.funico.interpreter.ExampleException;
import co.edu.unal.funico.interpreter.funico.interpreter.Extractor;
import co.edu.unal.funico.interpreter.funico.language.LexicalException;
import co.edu.unal.funico.interpreter.funico.language.SyntacticalException;

public class ExampleReader {

	private static final Logger LOG = LoggerFactory.getLogger(ExampleReader.class);

	private int maxEquations;
	private int maxNodesByEquation;
	private Extractor extractor;
	private List<String> examples;

	public ExampleReader(File example) {
		loadExampleFile(example);
	}

	private void loadExampleFile(File example) {
		Validate.isTrue(example.exists());
		String join = "";
		try {
			// Read file
			List<String> lines = FileUtils.readLines(example, StandardCharsets.UTF_8);

			// Save values
			this.maxEquations = Integer.parseInt(lines.remove(0));
			this.maxNodesByEquation = Integer.parseInt(lines.remove(0));
			this.examples = lines;

			// Extract information from examples
			join = StringUtils.join(lines, ";");
			extractor = new Extractor(join);

		} catch (IOException e) {
			LOG.error("Error al leer el archivo: {}", example, e);
			throw new RuntimeException(e);
		} catch (ExampleException | LexicalException | SyntacticalException ex) {
			LOG.error("No se pudo extraer la información de: {}", join, ex);
			throw new RuntimeException(ex);
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
