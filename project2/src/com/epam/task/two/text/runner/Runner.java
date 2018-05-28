package com.epam.task.two.text.runner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Runner {

	private static final Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		logger.debug("Logger had been started");
		runTextMenu();

	}
	
	private static void runTextMenu() {
		
	}

}
