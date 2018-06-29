package com.epam.task.two.text.exception;

public class ComponentMismatchException extends Exception{

    /**
     * Exception is thrown when a component of
     * a composition tree could not be found.
     * @author Alexey Yermachyonok
     * @version 1.0
     */
    
    private static final long serialVersionUID = 1L;

    public ComponentMismatchException() {
        super();
    }

    public ComponentMismatchException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ComponentMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentMismatchException(String message) {
        super(message);
    }

    public ComponentMismatchException(Throwable cause) {
        super(cause);
    }
}
