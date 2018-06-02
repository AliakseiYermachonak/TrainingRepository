package com.epam.task.two.text.runner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

import com.epam.task.two.text.exceptions.ComponentsSearchException;
import com.epam.task.two.text.executors.TaskExecutor;
import com.epam.task.two.text.executors.TextComponentParser;
import com.epam.task.two.text.executors.TextInputOutput;
import com.epam.task.two.text.executors.TextParser;

/**
 * Class to start the program. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class Runner {

	private static final Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("resources/log4j.properties");
		
		logger.debug("");
		logger.debug("=================================================");
		logger.debug("Logger had been started");
		
		Component mainComponent = new TextComponent();
		
		logger.info("Creating new text parsers.");
		TextParser textParser = new TextParser();
		TextComponentParser paragraphParser = new TextComponentParser();
		TextComponentParser sentenceParser = new TextComponentParser();
		TextComponentParser wordParser = new TextComponentParser();
		
		logger.info("Linking chain of responsibility.");
		textParser.setNextParser(paragraphParser);
		paragraphParser.setNextParser(sentenceParser);
		sentenceParser.setNextParser(wordParser);
	
		logger.info("Creating new text Composition.");
		mainComponent.addAll(textParser.parse(TextInputOutput.readFromFile(), TextType.TEXT));

		//mainComponent.addAll(ParserSt.parseText(TextInputOutput.readFromFile(), TextType.TEXT));
		
		logger.info("Decomposing objects to file.");
		TextInputOutput.writeToFileText(mainComponent);
		
		logger.info("Searching for the unique words from the fitst sentence.");
		TaskExecutor.uniqueWordSearch(mainComponent);
		try {
			logger.info("Sorting sentences by the amount of words.");
			TaskExecutor.componentSort(TaskExecutor.getTextTypeComponents(mainComponent, TextType.SENTENCE));
		} catch (ComponentsSearchException e) {
			logger.error(e);
		}
		logger.info("Everything is done. Goodbye.");
	}	
}
