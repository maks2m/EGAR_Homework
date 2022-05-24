package ru.elkin.egar.logic;

import ru.elkin.egar.exception.NotEvenNumException;
import ru.elkin.egar.exception.NotNumException;

public class EvenNum {

    public static void checkEvenNum(int intNum) throws NotEvenNumException {

        if ((intNum % 2) == 0) {
            return;
        } else {
            throw new NotEvenNumException("This num is not even: ", intNum);
        }

    }


    public static boolean isNumeric(String string) throws NotNumException {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            throw new NotNumException("This input is not num: ", string);
        }
    }
}
