package com.library.exceptions;


public class NoBookException extends NullPointerException {

    public NoBookException() {
        System.err.println("Number of books cannot be zero!");
    }
    
}
