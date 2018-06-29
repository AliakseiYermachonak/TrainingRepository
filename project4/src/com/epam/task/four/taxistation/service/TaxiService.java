package com.epam.task.four.taxistation.service;

import java.util.Collections;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.four.taxistation.exception.TaxiNotFoundException;
import com.epam.task.four.taxistation.model.Cab;
import com.epam.task.four.taxistation.model.TaxiStation;
import com.epam.task.four.taxistation.xmlreader.Director;
import com.epam.task.four.taxistation.xmlreader.XMLValidator;

/**
 * Class is used for executing commands according to the main menu
 * 
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class TaxiService {

    final static Logger LOGGER = Logger.getLogger(TaxiService.class);

    /**
     * Attribute is an entity object with our list of cabs to work with
     * 
     * @see TaxiStation
     */
    private TaxiStation taxiStation;

    /**
     * Creates a new service class for each entity object
     * 
     * @param taxiStation
     * @see TaxiStation
     */
    public TaxiService(TaxiStation taxiStation) {
        this.taxiStation = taxiStation;
        PropertyConfigurator.configure("properties/log4j.properties");
    }

    /**
     * Generates a list of random cabs with random parameters
     * 
     * @see Cab
     */
    public void generateCabList() {
        LOGGER.debug("Generating a list of random cabs.");
        if (!taxiStation.getCabList().isEmpty()) {
            taxiStation.getCabList().clear();
        }
        int n = (int) (Math.random() * 6 + 5);
        for (int i = 0; i < n; i++) {
            int price = ((int) (Math.random() * 600 + 500)) * (i + 1);
            int speed = ((int) (Math.random() * 16 + 15)) * (i + 1);
            int fuel = ((int) (Math.random() * 16 + 5));
            taxiStation.addCab(new Cab(speed, fuel, price));
        }
        LOGGER.debug("List of cabs has been generated.");
        showCabList();
    }

    /**
     * Shows each cab from the list
     * 
     * @see Cab
     */
    public void showCabList() {
        LOGGER.debug("Showing cab list on console.");
        if (taxiStation.getCabList().isEmpty()) {
            LOGGER.info("Note: your taxi station is empty");
        } else {
            for (Cab cab : taxiStation.getCabList()) {
                LOGGER.info(cab);
            }
        }
    }

    /**
     * Sorts cabs according to their fuel consumption
     */
    public void sortCabs() {
        LOGGER.debug("Sorting cabs.");
        if (taxiStation.getCabList().isEmpty()) {
            LOGGER.info("Note: your taxi station is empty");
        } else {
            Collections.sort(taxiStation.getCabList());
            showCabList();
        }
    }

    /**
     * Shows the total taxi station price
     * 
     * @return int price
     */
    public int showPrice() {
        LOGGER.debug("Loading taxi station total price.");
        if (taxiStation.getCabList().isEmpty()) {
            LOGGER.info("Note: your taxi station is empty");
        }
        int sum = 0;
        for (Cab cab : taxiStation.getCabList()) {
            sum += cab.getPrice();
        }
        LOGGER.info("Total taxi station price: " + sum);
        return sum;
    }

    /**
     * Looks for a cab with speed parameter from the received delta. Asks to input
     * delta from console
     * 
     * @return The first cab, suitable for the input data.
     * @throws TaxiNotFoundException
     * @see Cab
     */
    public Cab showCabWithSpeed() throws TaxiNotFoundException {
        LOGGER.debug("Reading data for the cab search.");
        if (taxiStation.getCabList().isEmpty()) {
            LOGGER.info("Note: your taxi station is empty");
        }

        int a, b;

        Scanner scanner = new Scanner(System.in);
        try {
            LOGGER.info("Bottom line of taxi speed is ");
            a = Integer.parseInt(scanner.next());
            LOGGER.info("Upper bound of taxi speed is ");
            b = Integer.parseInt(scanner.next());

            LOGGER.debug("Looking for a cab from the range.");

            for (Cab cab : taxiStation.getCabList()) {
                if (cab.getSpeed() >= a && cab.getSpeed() <= b) {
                    LOGGER.info("You are looking for a \n" + cab);
                    return cab;
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.info("Please, enter a number");
            LOGGER.error("Number input problem. \nStack:", e);
        }
        throw new TaxiNotFoundException();
    }

    /**
     * Reads a list of cabs from XML with SAX
     * 
     * @see Cab
     */
    public void readSAXCabList() {
        if (XMLValidator.validateList()) {
            taxiStation.setCabList(new Director().useSAX());
        }
        showCabList();
    }

    /**
     * Reads a list of cabs from XML with StAX
     * 
     * @see Cab
     */
    public void readStAXCabList() {
        if (XMLValidator.validateList()) {
            taxiStation.setCabList(new Director().useStAX());
        }
        showCabList();
    }

    /**
     * Reads a list of cabs from XML with DOM
     * 
     * @see Cab
     */
    public void readDOMCabList() {
        if (XMLValidator.validateList()) {
            taxiStation.setCabList(new Director().useDOM());
        }
        showCabList();
    }
}
