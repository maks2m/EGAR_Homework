package ru.elkin.egar.exception;

public class NotEvenNumException extends Exception {

    private int num;

    public int getNum() {
        return num;
    }

    public NotEvenNumException(String message, int num) {
        super(message);
        this.num = num;
    }

}
