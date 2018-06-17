package com.epam.task.three.threading.entity;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This class is used to describe a pasenger's thread
 * after he left the plane.
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class Passenger implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Passenger.class);
	
	private static Integer amount = 0;
	private int fromPlane;
	private Integer ticketToPlane;
	private boolean isWantToChange;
	private boolean isWantToLeave;
	private Airport airport;
	private int id;

	Passenger(int fromPlane, Airport airport) {
		PropertyConfigurator.configure("properties/log4j.properties");
		this.fromPlane = fromPlane;
		ticketToPlane = new Integer(((int) (Math.random() * (17-fromPlane))) + (fromPlane));
		isWantToLeave = (Math.random() > 0.7);
		isWantToChange = (Math.random() > 0.5);
		this.airport = airport;
		amount++;
		id = amount;
	}

	/**
	 * The passenger's behavior is next:
	 * After leaving the plain and getting to the airport he can:
	 *  - leave it and go away, thread is dead;
	 *  - wait for another plane;
	 *  - change the ticket with someone else,
	 *  but if it is expired passenger will leave the airport.
	 */
	@Override
	public void run() {
		LOGGER.info("\t\t\t" + this + " entered waiting-room.");
		try {
			while (true) {
				if (!isWantToLeave) {
					if (!isWantToChange) {
						synchronized (Airport.TERMINAL_BUSY) {
							if ((airport.getPlanesLanded() - 3 <= ticketToPlane)
									&& (airport.getPlanesLanded() >= ticketToPlane)) {
								LOGGER.info("\t\t\tPassenger " + id + " went to plane " + ticketToPlane);
								break;
							}
						}
						Thread.sleep(300);
						if (airport.getPlanesLanded() - 3 > ticketToPlane) {
							LOGGER.info("\t\t\tPassenger " + id + " is leaving with ticket " + ticketToPlane);
							break;
						}

					} else {
						LOGGER.info("\t\t\t\t\tPassenger " + id + " wants to change the ticket " + ticketToPlane);
						if (ticketToPlane > airport.getPlanesLanded() - 2) {
							synchronized (airport.exchangeRoom) {
								if (airport.exchangeRoom.isEmpty()) {
									airport.exchangeRoom.setEmpty(false);
									airport.exchangeRoom.setPassenger(this);
									LOGGER.info("\t\t\t\t\tPassenger " + id + " with ticket " + ticketToPlane + " is waiting for exchange.");
								} else {
									Thread.sleep(100);
									if (airport.exchangeRoom.getPassenger().ticketToPlane > airport.getPlanesLanded() - 2) {
										ticketToPlane = Airport.EXCHANGER.exchange(ticketToPlane);
										isWantToChange = false;
										LOGGER.debug("Passenger " + id + " with ticket " + ticketToPlane + " took another part of exchanger.");
										airport.exchangeRoom.setEmpty(true);
										LOGGER.info("\t\t\t\t\tPassenger " + id + " with ticket " + ticketToPlane + " is satisfied.");
										LOGGER.info("\t\t\t\t\tPassenger " + airport.exchangeRoom.getPassenger().id + " with ticket "
													+ airport.exchangeRoom.getPassenger().ticketToPlane + " is satisfied.");
									} else {
										LOGGER.info("\t\t\t\t\tPassenger " + airport.exchangeRoom.getPassenger().id 
												+ " has lost the ticket " + airport.exchangeRoom.getPassenger().ticketToPlane + " valid time.");
										airport.exchangeRoom.getPassenger().isWantToLeave = true;
										int a = Airport.EXCHANGER.exchange(new Integer(0));
										airport.exchangeRoom.setPassenger(this);
									}
								}
							}
							if (isWantToChange) {
								LOGGER.debug("Passenger " + id + " with ticket " + ticketToPlane + " took a first Exchanger part.");
								ticketToPlane = Airport.EXCHANGER.exchange(ticketToPlane);
								isWantToChange = false;
							}
						} else {
							LOGGER.info("\t\t\t\t\tPassenger " + id + " has lost the ticket valid time.");
							isWantToChange = false;
						}
					}
				} else {
					LOGGER.info("\t\t\tPassenger " + id + " just leaving.");
					break;
				}
			}
		} catch (InterruptedException e) {
			LOGGER.error("This terrible passengers error " + e);
		}
	}

	@Override
	public String toString() {
		return "Passenger id=" + id + " [fromPlane=" + fromPlane + ", ticketToPlane=" + ticketToPlane
				+ ", isWantToChange=" + isWantToChange + ", isWantToLeave=" + isWantToLeave + "]";
	}

}
