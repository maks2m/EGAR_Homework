package ru.elkin.egar.exception;

public class NotNumException extends NumberFormatException{

    private String input;

    public String getInput() {
        return input;
    }

    public NotNumException(String message, String input) {
        super(message);
        this.input = input;
    }

}
