package com.epam.task.four.taxistation.exception;

/**
 * Exception is thrown when there is no such a cab
 * with a given parameters in a list of existing cabs.
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TaxiNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public TaxiNotFoundException() {
        super();
    }    
}
