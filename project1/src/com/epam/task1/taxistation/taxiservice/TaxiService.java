package com.epam.task1.taxistation.taxiservice;

import java.util.Collections;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task1.taxistation.exception.TaxiNotFoundException;
import com.epam.task1.taxistation.model.Cab;
import com.epam.task1.taxistation.model.TaxiStation;
/**
 * Class is used for executing commands
 * according to the main menu
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class TaxiService {
	
	final static Logger logger = Logger.getLogger(TaxiService.class);
	
	/**
	 * Attribute is an entity object
	 * with our list of cabs to work with
	 * @see TaxiStation
	 */
	private TaxiStation taxiStation;

	/**
	 * Creates a new service class
	 * for each entity object 
	 * @param taxiStation
	 * @see TaxiStation
	 */
	public TaxiService(TaxiStation taxiStation) {
		this.taxiStation = taxiStation;
		PropertyConfigurator.configure("properties/log4j.properties");
	}

	/**
	 * Generates a list of random cabs
	 * with random parameters
	 * @see Cab
	 */
	public void generateCabList() {
		logger.debug("Generating a list of random cabs.");
		if (!taxiStation.getCabList().isEmpty()) {
			taxiStation.getCabList().clear();
		}
		int n = (int) (Math.random()*6 + 5);
		for (int i = 0; i < n; i++) {
			int price = ((int) (Math.random()*600 + 500)) * (i+1);
			int speed = ((int) (Math.random()*16 + 15)) * (i+1);
			int fuel = ((int) (Math.random()*16 + 5));
			taxiStation.addCab(new Cab(speed, fuel, price));
		}
		logger.debug("List of cabs has been generated.");
		showCabList();
	}
	
	/**
	 * Shows each cab from the list
	 * @see Cab
	 */
	public void showCabList() {
		logger.debug("Showing cab list on console.");
		if (taxiStation.getCabList().isEmpty()) {
			logger.info("Note: your taxi station is empty");
		} else {
			for (Cab cab: taxiStation.getCabList()) {
				logger.info(cab);
			}
		}
	}
	
	/**
	* Sorts cabs according to their fuel consumption
	*/
	public void sortCabs(){
		logger.debug("Sorting cabs.");
		if (taxiStation.getCabList().isEmpty()) {
			logger.info("Note: your taxi station is empty");
		} else {
			Collections.sort(taxiStation.getCabList());
			showCabList();
		}
	}
	
	/**
	 * Shows the total taxi station price
	 * @return int price
	 */
	public int showPrice() {
		logger.debug("Loading taxi station total price.");
		if (taxiStation.getCabList().isEmpty()) {
			logger.info("Note: your taxi station is empty");
		}
		int sum = 0;
		for (Cab cab: taxiStation.getCabList()) {
			sum += cab.getPrice();
		}
		logger.info("Total taxi station price: " + sum);
		return sum;
	}
	
	/**
	 * Looks for a cab with speed parameter from the received delta.
	 * Asks to input delta from console
	 * @return The first cab, suitable for the input data.
	 * @throws TaxiNotFoundException
	 * @see Cab
	 */
	public Cab showCabWithSpeed() throws TaxiNotFoundException {
		logger.debug("Reading data for the cab search.");
		if (taxiStation.getCabList().isEmpty()) {
			logger.info("Note: your taxi station is empty");
		}

		int a, b;
		
		Scanner scanner = new Scanner(System.in);
		try {
			logger.info("Bottom line of taxi speed is ");
			a = Integer.parseInt(scanner.next());
			logger.info("Upper bound of taxi speed is ");
			b = Integer.parseInt(scanner.next());
			
			logger.debug("Looking for a cab from the range.");
			
			for (Cab cab: taxiStation.getCabList()) {
				if (cab.getSpeed()>=a && cab.getSpeed()<=b) {
					logger.info("You are looking for a \n" + cab);
					return cab;
				}
			}
		} catch (NumberFormatException e) {
			logger.info("Please, enter a number");
			logger.error("Number input problem. \nStack:", e);
		}
		throw new TaxiNotFoundException();	
	}
}
