package com.library.exceptions;

public class TextLengthException extends Exception{

    public TextLengthException() {
        System.err.println("Maximmum length is 500 characters");;
    }
}
