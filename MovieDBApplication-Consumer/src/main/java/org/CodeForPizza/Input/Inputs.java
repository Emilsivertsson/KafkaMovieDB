package org.CodeForPizza.Input;

import org.CodeForPizza.output.Output;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {

    Scanner scanner = new Scanner(System.in);

    public String askForString(String message) {
        String title = "";
        do {
            try {
                System.out.println(message);
                title = scanner.nextLine();
                if (title.isEmpty()) {
                    System.out.println(Output.titleCantBeEmpty);
                }
                if (title.contains("å") || title.contains("ä") || title.contains("ö")) {
                    System.out.println(Output.titleCantBeSwe);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (title.contains("å") || title.contains("ä") || title.contains("ö") || title.isEmpty() || title.isBlank());
        return title;
    }

    public String askForIntAsString(String message) {
        int year = 0;

        do {
            try {
                System.out.println(message);
                year = Integer.parseInt(scanner.nextLine());
                if (year < 1888 || year > 2024) {
                    System.out.println(Output.YearOutOfRange);
                }
                if (year == 0) {
                    System.out.println(Output.yearCantBeZero);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (year < 1888 || year > 2024);
        return String.valueOf(year);
    }


    public int askForInt(String message) {
        int id = 0;
        do {
            try {
                System.out.println(message);
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println(Output.idCantBeZero);
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (id <= 0);
        return id;
    }
}
