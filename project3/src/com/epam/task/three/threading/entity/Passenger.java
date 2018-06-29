package com.epam.task.three.threading.entity;

import org.apache.log4j.Logger;

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
    private boolean wantToChange;
    private boolean wantToLeave;
    private Airport airport;
    private int id;

    Passenger(int fromPlane, Airport airport) {
        this.fromPlane = fromPlane;
        ticketToPlane = new Integer(((int) (Math.random() * (17-fromPlane))) + (fromPlane));
        wantToLeave = (Math.random() > 0.7);
        wantToChange = (Math.random() > 0.5);
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
                if (!wantToLeave) {
                    if (!wantToChange) {
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
                        airport.tryExchangeRoom(this);
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

    
    
    public boolean isWantToChange() {
		return wantToChange;
	}

	public void setWantToChange(boolean wantToChange) {
		this.wantToChange = wantToChange;
	}

	public int getId() {
		return id;
	}

	public Integer getTicketToPlane() {
		return ticketToPlane;
	}

	public void setTicketToPlane(Integer ticketToPlane) {
		this.ticketToPlane = ticketToPlane;
	}

	public boolean isWantToLeave() {
		return wantToLeave;
	}

	public void setWantToLeave(boolean wantToLeave) {
		this.wantToLeave = wantToLeave;
	}

	@Override
    public String toString() {
        return "Passenger id=" + id + " [fromPlane=" + fromPlane + ", ticketToPlane=" + ticketToPlane
                + ", isWantToChange=" + wantToChange + ", isWantToLeave=" + wantToLeave + "]";
    }

}
