package com.ecobike.exception;

/**
 * Exception for throwing if data source with data specified by user
 * has wrong data format and/or can not be parsed.
 */
public class IllegalDataSourceException extends Exception {
    public IllegalDataSourceException(Throwable cause) {
        super(cause);
    }

    public IllegalDataSourceException() {
    }
}
