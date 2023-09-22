package org.CodeForPizza.output;


public class Output {
    public static void printMenu() {
        System.out.println("Welcome to the movie database. Please choose an option:");
        System.out.println("1. Add a movie to the database");
        System.out.println("2. list all movies in the database");
        System.out.println("3. update a movie in the database");
        System.out.println("4. delete a movie from the database");
        System.out.println("5. exit program");
    }

    public static void thankYou() {
        System.out.println("Thank you for using the movie database. Goodbye.");

    }

    public static void askForYear() {
        System.out.print("Please enter the year: ");
    }

    public static void askForTitle() {
        System.out.print("Please enter the title: ");
    }

    public static void printResponse() {
        System.out.println("Movie information sent to the database.");
    }


}
