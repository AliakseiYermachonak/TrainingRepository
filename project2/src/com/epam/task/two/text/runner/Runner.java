package com.epam.task.two.text.runner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.exception.ComponentMismatchException;
import com.epam.task.two.text.executor.TaskExecutor;
import com.epam.task.two.text.executor.TextInputOutput;
import com.epam.task.two.text.parser.SentenceParser;
import com.epam.task.two.text.parser.Parser;
import com.epam.task.two.text.parser.ParagraphParser;
import com.epam.task.two.text.parser.TextParser;

/**
 * Class to start the program. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class Runner {

	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("resource/log4j.properties");
		LOGGER.debug("");
		LOGGER.debug("=================================================");
		LOGGER.debug("Logger had been started");
		
		Component mainComponent = new TextComponent();
		
		LOGGER.info("Creating new text parsers.");
		Parser textParser = new TextParser();
		Parser paragraphParser = new ParagraphParser();
		Parser sentenceParser = new SentenceParser();
		Parser wordParser = new SentenceParser();
		
		LOGGER.info("Linking chain of responsibility.");
		textParser.setNextParser(paragraphParser);
		paragraphParser.setNextParser(sentenceParser);
		sentenceParser.setNextParser(wordParser);
	
		LOGGER.info("Creating new text Composition.");
		mainComponent.addAll(textParser.parse(TextInputOutput.readFromFile()));
		
		LOGGER.info("Decomposing objects to file.");
		TextInputOutput.writeToFileText(mainComponent);

		LOGGER.info("Searching for the unique words from the fitst sentence.");
		TaskExecutor.uniqueWordFromFirstSentence(mainComponent);
		
		try {
			LOGGER.info("Sorting sentences by the amount of words.");
			TaskExecutor.componentSort(TaskExecutor.getComponentsFromLevel(mainComponent, 1));
		} catch (ComponentMismatchException e) {
			LOGGER.error("Exception in searching components to be found " + e);
		}
		LOGGER.info("Everything is done. Goodbye.");
	}	
}
