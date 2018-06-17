package com.epam.task.three.threading.runner;

import org.apache.log4j.Logger;

import com.epam.task.three.threading.entity.Airport;

/**
 * Class to start the program. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class Runner {

	private final static Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		try {
			new Airport(16).work();
		} catch (InterruptedException e) {
			LOGGER.error("Whole airport is burnning! " + e);
		}
	}
}
