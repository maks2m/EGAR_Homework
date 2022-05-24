package ru.elkin.egar;

import ru.elkin.egar.exception.NotEvenNumException;
import ru.elkin.egar.exception.NotNumException;
import ru.elkin.egar.logic.EvenNum;
import ru.elkin.egar.logic.FindString;
import ru.elkin.egar.model.ListInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        final int LENGTH_LIST = 10;
        ListInput ListInput = new ListInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FindString findString = new FindString();
        System.out.print("Enter list num: ");

        for (int i = 0; i < LENGTH_LIST; i++) {
            ListInput.addNewElement(reader.readLine());
        }

        for (String input : ListInput.getList()) {

            findString.setFindString("exit");
            findString.setRootString(input);
            if(findString.find()) {
                System.out.println("Exit!");
                System.exit(0);
            }

            try {
                if (EvenNum.isNumeric(input)) {
                    int intNum = Integer.parseInt(input);

                    try {
                        EvenNum.checkEvenNum(intNum);
                        System.out.println("This num is even: " + intNum);

                    } catch (NotEvenNumException e) {
                        System.out.print(e.getMessage());
                        System.out.println(e.getNum());
                    }


                }
            } catch (NotNumException e) {
                System.out.print(e.getMessage());
                System.out.println(e.getInput());
            }
        }

        System.out.println(ListInput.getList().stream().map(Integer::parseInt).collect(Collectors.partitioningBy((p) -> p % 2 == 0)));

    }
}

