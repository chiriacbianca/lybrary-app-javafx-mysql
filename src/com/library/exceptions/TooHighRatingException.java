package com.library.exceptions;


public class TooHighRatingException extends Exception{

    public TooHighRatingException() {
        System.err.println("Rating must be between 1 and 5!");
    }
}
