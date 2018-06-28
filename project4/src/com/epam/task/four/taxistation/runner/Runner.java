package com.epam.task.four.taxistation.runner;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.four.taxistation.exception.TaxiNotFoundException;
import com.epam.task.four.taxistation.model.TaxiStation;
import com.epam.task.four.taxistation.service.TaxiService;

/**
 * Main class to start the Taxi Station program,
 * contains methods for working with a console 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class Runner {

	static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("properties/log4j.properties");
		LOGGER.debug("Logger had been started");
		runTaxiStationMenu(new TaxiStation());
	}
	
	/**
	 * Method gets an option from the user and executes it.
	 * @param TaxiStation
	 * @see TaxiStation
	 */
	public static void runTaxiStationMenu(TaxiStation taxiStation) {
		TaxiService taxiService = new TaxiService(taxiStation);
		LOGGER.debug("TaxiService created.");
		while (true) {
			showMenuLines();
			LOGGER.debug("Menu loaded");
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
						LOGGER.info("There are no a suchlike taxi");
						LOGGER.error("Taxi was not found. \nStack:", e);
						continue;
					}
				}
				case 5: {
					LOGGER.debug("reading Cab List with SAX parser");
					taxiService.readSAXCabList();
					break;
				}
				case 6: {
					LOGGER.debug("reading Cab List with StAX parser");
					taxiService.readStAXCabList();
					break;
				}
				case 7: {
					LOGGER.debug("reading Cab List with DOM parser");
					taxiService.readDOMCabList();
					break;
				}
				case 0: {
					LOGGER.info("You are leaving the program, \nGoodbye!");
					LOGGER.debug("Leaving the program.");
					return;
				}
				default: {
					LOGGER.info("Please, try again.");
					break;
				}
			}
		}
	}
	
	/**
	 * Prints the menu on console
	 */
	public static void showMenuLines() {
		LOGGER.info("Welcom to our taxi station, \nselect one of the next options:" );
		LOGGER.info("1 -- Generate a set of random taxis");
		LOGGER.info("2 -- Count the total price");
		LOGGER.info("3 -- Sort the taxis according to the fuel consumption");
		LOGGER.info("4 -- Get the cab with speed from the range");
		LOGGER.info("5 -- Read a set of cabs from XML with SAX");
		LOGGER.info("6 -- Read a set of cabs from XML with StAX");
		LOGGER.info("7 -- Read a set of cabs from XML with DOM");
		LOGGER.info("0 -- Exit");
	}
	
	/**
	 * Reads the number of selected option
	 * @return int parameter of chosen option
	 */
	public static int doCommand() {
		LOGGER.debug("Reading the command.");
		int input = -1;
		try {
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextInt();
			if (input == 0) {
				scanner.close();
			}
		} catch(InputMismatchException e) {
			LOGGER.info("Input is not a number.");
			LOGGER.error("Command input mistake. \nStack:", e);
		}
		return input;
	}
}
