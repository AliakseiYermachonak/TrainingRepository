package com.epam.task.three.threading.entity;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This class is used to describe the plane's thread
 * which is coming or leaving the airport. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class Plane implements Runnable {
    
    private final static Logger LOGGER = Logger.getLogger(Plane.class);

    private int planeNumber;
    private int passengers;
    private Airport airport;

    public Plane(int planeNumber, Airport airport) {
        this.planeNumber = planeNumber;
        passengers = ((int) (Math.random() * 3)) + 2;
        this.airport = airport;
    }

    /**
     * The life of the plane thread consists from the next steps:
     * - gets to the airport;
     * - waits for the free terminal;
     * - landing and disembarking passengers;
     * - preparing;
     * - embarking passengers;
     * - flying away.
     */
    @Override
    public void run() {
        LOGGER.info("Plane " + planeNumber + " is waiting in the sky." );
        try {
            Airport.SEMAPHORE.acquire();
            int terminalNumber = -1;
            
            synchronized (Airport.TERMINAL_BUSY) {
                for (int i = 0; i < Airport.TERMINAL_BUSY.length; i++) {
                    if (!Airport.TERMINAL_BUSY[i]) {
                        Airport.TERMINAL_BUSY[i] = true;
                        terminalNumber = i;
                        LOGGER.info("Plane " + planeNumber + " has landed and waiting at the terminal " + i + ".");
                        airport.setPlanesLanded(airport.getPlanesLanded() + 1);
                        LOGGER.debug(airport.getPlanesLanded() + " planes landed.");
                        LOGGER.debug("Terminal " + terminalNumber + " is occupied.");
                        break;
                    }
                }
            }
            
            for (int i = 0; i < passengers; i++) {
                Thread.sleep(50);
                Passenger passenger = new Passenger(planeNumber, airport);
                new Thread(passenger).start();
                Thread.sleep(50);
            }
            
            Thread.sleep(2000);

            synchronized (Airport.TERMINAL_BUSY) {
                Airport.TERMINAL_BUSY[terminalNumber] = false;
                LOGGER.debug("Plane " + planeNumber + " released the terminal " + terminalNumber + ".");
            }

            Airport.SEMAPHORE.release();
            LOGGER.info("Plane " + planeNumber + " left the airport.");
        } catch (InterruptedException e) {
            LOGGER.error("Semaphore flying planes error " + e);
        }
    }

}
