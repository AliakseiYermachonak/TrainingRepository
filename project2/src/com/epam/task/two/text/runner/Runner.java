package com.epam.task.two.text.runner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;
import com.epam.task.two.text.exceptions.WTFException;
import com.epam.task.two.text.executors.TaskExecutor;
import com.epam.task.two.text.executors.TextComponentParser;
import com.epam.task.two.text.executors.TextInputOutput;
import com.epam.task.two.text.executors.TextParser;

public class Runner {

	private static final Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		logger.debug("");
		logger.debug("=================================================");
		logger.debug("Logger had been started");
		
		Component mainComponent = new TextComponent();
		
		TextParser textParser = new TextParser();
		TextComponentParser paragraphParser = new TextComponentParser();
		TextComponentParser sentenceParser = new TextComponentParser();
		TextComponentParser wordParser = new TextComponentParser();
		
		
		textParser.setNextParser(paragraphParser);
		paragraphParser.setNextParser(sentenceParser);
		sentenceParser.setNextParser(wordParser);
	
		mainComponent.addAll(textParser.parse(TextInputOutput.readFromFile(), TextType.TEXT));

		//mainComponent.addAll(ParserSt.parseText(TextInputOutput.readFromFile(), TextType.TEXT));
		TextInputOutput.writeToFileText(mainComponent);
		TaskExecutor.uniqueWordSearch(mainComponent);
		try {
			TaskExecutor.componentSort(TaskExecutor.getTextTypeComponents(mainComponent, TextType.SENTENCE));
		} catch (WTFException e) {
			e.printStackTrace();
		}
		
	}	
}
