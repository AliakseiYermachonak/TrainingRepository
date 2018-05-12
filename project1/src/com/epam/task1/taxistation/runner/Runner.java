package com.epam.task1.taxistation.runner;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task1.taxistation.exception.TaxiNotFoundException;
import com.epam.task1.taxistation.model.TaxiStation;
import com.epam.task1.taxistation.taxiservice.TaxiService;

/**
 * Main class to start the Taxi Station program,
 * contains methods for working with a console 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class Runner {

	static final Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("properties/log4j.properties");
		logger.debug("Logger had been started");
		runTaxiStationMenu(new TaxiStation());
	}
	
	/**
	 * Method gets an option from the user and executes it.
	 * @param TaxiStation
	 * @see TaxiStation
	 */
	public static void runTaxiStationMenu(TaxiStation taxiStation) {
		TaxiService taxiService = new TaxiService(taxiStation);
		logger.debug("TaxiService created.");
		while (true) {
			showMenuLines();
			logger.debug("Menu loaded");
			switch (doCommand()) {
				case 1: { 
					taxiService.generateCabList();
					break;
				}
				case 2: { 
					taxiService.showPrice();
					break;
				}
				case 3: { 
					taxiService.sortCabs();
					break;
				}
				case 4: {
					try {
						taxiService.showCabWithSpeed();
						break;
					} catch (TaxiNotFoundException e) {
						logger.info("There are no a suchlike taxi");
						logger.error("Taxi was not found. \nStack:", e);
						continue;
					}
				}
				case 0: {
					logger.info("You are leaving the program, \nGoodbye!");
					logger.debug("Leaving the program.");
					return;
				}
				default: {
					logger.info("Please, try again.");
					break;
				}
			}
		}
	}
	
	/**
	 * Prints the menu on console
	 */
	public static void showMenuLines() {
		logger.info("Welcom to our taxi station, \nselect one of the next options:" );
		logger.info("1 -- Generate a set of random taxis");
		logger.info("2 -- Count the total price");
		logger.info("3 -- Sort the taxis according to the fuel consumption");
		logger.info("4 -- Get the cab with speed from the range");
		logger.info("0 -- Exit");
	}
	
	/**
	 * Reads the number of selected option
	 * @return int parameter of chosen option
	 */
	public static int doCommand() {
		logger.debug("Reading the command.");
		int input = -1;
		try {
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextInt();
			if (input == 0) {
				scanner.close();
			}
		} catch(InputMismatchException e) {
			logger.info("Input is not a number.");
			logger.error("Command input mistake. \nStack:", e);
		}
		return input;
	}

}
