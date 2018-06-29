package com.epam.task.three.threading.entity;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class contains fields connected with 
 * organization of planes flights and terminals 
 * of the airport where this planes can board
 * and disembark passengers. Also has a field of
 * a room for exchanging tickets between passengers
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class Airport {
    
    private final static Logger LOGGER = Logger.getLogger(Airport.class);
    private static volatile Airport instance;
    static final Exchanger<Integer> EXCHANGER = new Exchanger<>();
    Boolean isExchangeReady = new Boolean(false);
    static final boolean[] TERMINAL_BUSY = new boolean[4];
    static final Semaphore SEMAPHORE = new Semaphore(4, true);
    ExchangeRoom exchangeRoom;
    int planesToCome;
    private int planesCame;
    private int planesLanded;

    private Airport(int planesToCome){
        PropertyConfigurator.configure("properties/log4j.properties");
        LOGGER.info("Creating new airport.");
        this.planesToCome = planesToCome;
        exchangeRoom = new ExchangeRoom();
        planesCame = 0;
        planesLanded = 0;
    }
    
    public static Airport getInstance(int i) {
    	if (instance == null) {
    		synchronized(Airport.class) {
    			if (instance == null) {
    				instance = new Airport(i);
    			}
    		}
    	}
    	return instance;
    }
    
    /**
     * Airport starts working. Different planes are coming as a new threads.
     * @throws InterruptedException
     * @see Plane
     */
    public void work() throws InterruptedException {
        LOGGER.info("Putting planes into the sky.");
        for (planesCame = 1; planesCame <= planesToCome; planesCame++) {
            new Thread(new Plane(planesCame, this)).start();
            Thread.sleep(500);
        }     
    }
    
    /**
     * Number of planes that landed after airport
     * had started to work.
     * @return int of the landed planes.
     */
    public int getPlanesLanded() {
        return planesLanded;
    }
    
    /**
     * Number of planes that landed after airport
     * had started to work.
     * @param int of the landed planes.
     */
    public void setPlanesLanded(int planesLanded) {
        this.planesLanded = planesLanded;
    }

    
    public void tryExchangeRoom(Passenger passenger) {
    	try {
	    	LOGGER.info("\t\t\t\t\tPassenger " + passenger.getId() + " wants to change the ticket "
	    			+ passenger.getTicketToPlane());
	        if (passenger.getTicketToPlane() > getPlanesLanded() - 2) {
	            synchronized (exchangeRoom) {
	                if (exchangeRoom.isEmpty()) {
	                    exchangeRoom.setEmpty(false);
	                    exchangeRoom.setPassenger(passenger);
	                    LOGGER.info("\t\t\t\t\tPassenger " + passenger.getId() + " with ticket "
	                    		+ passenger.getTicketToPlane() + " is waiting for exchange.");
	                } else {
	                    Thread.sleep(100);
	                    if (exchangeRoom.getPassenger().getTicketToPlane() > getPlanesLanded() - 2) {
	                        passenger.setTicketToPlane(EXCHANGER.exchange(passenger.getTicketToPlane()));
	                        passenger.setWantToChange(false);
	                        LOGGER.debug("Passenger " + passenger.getId() + " with ticket " + passenger.getTicketToPlane() 
	                        	+ " took another part of exchanger.");
	                        exchangeRoom.setEmpty(true);
	                        LOGGER.info("\t\t\t\t\tPassenger " + passenger.getId() + " with ticket " + passenger.getTicketToPlane() + " is satisfied.");
	                        LOGGER.info("\t\t\t\t\tPassenger " + exchangeRoom.getPassenger().getId() + " with ticket "
	                                    + exchangeRoom.getPassenger().getTicketToPlane() + " is satisfied.");
	                    } else {
	                        LOGGER.info("\t\t\t\t\tPassenger " + exchangeRoom.getPassenger().getId() 
	                                + " has lost the ticket " + exchangeRoom.getPassenger().getTicketToPlane() + " valid time.");
	                        exchangeRoom.getPassenger().isWantToLeave();
	                        int a = Airport.EXCHANGER.exchange(new Integer(0));
	                        exchangeRoom.setPassenger(passenger);
	                    }
	                }
	            }
	            if (passenger.isWantToChange()) {
	                LOGGER.debug("Passenger " + passenger.getId() + " with ticket " + passenger.getTicketToPlane() + " took a first Exchanger part.");
	                passenger.setTicketToPlane(EXCHANGER.exchange(passenger.getTicketToPlane()));
	                passenger.setWantToChange(false);
	            }
	        } else {
	            LOGGER.info("\t\t\t\t\tPassenger " + passenger.getId() + " has lost the ticket valid time.");
	            passenger.setWantToChange(false);
	        }
    	} catch (InterruptedException e) {
    		LOGGER.error("We have a problem in ticket exchang " + e);
    	}
    }
    
    /**
     * Inner class to describe a room for Exchanger pattern,
     * room is used to check is passenger able to change the 
     * ticket or not.
     * @author Alexey Yermachyonok
     * @version 1.0
     */

    public class ExchangeRoom {
        private boolean empty;
        private Passenger passenger;
        
        
        private ExchangeRoom() {
            empty = true;
        }

        /**
         * Shows was there anybody to change the ticket.
         * @return boolean state of the room.
         */
        public boolean isEmpty() {
            return empty;
        }

        /**
         * Sets the room empty if a person left the room
         * or occupied if person entered the room.
         * @param boolean state of the room.
         */
        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        /**
         * Get the passenger who want to change 
         * his ticket.
         * @return Passenger from the room.
         * @see Passenger.
         */
        public Passenger getPassenger() {
            return passenger;
        }

        /**
         * Set the passenger who wants to change
         * his ticket.
         * @param Passenger to change the ticket.
         * @see passenger.
         */
        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }
    }
}
