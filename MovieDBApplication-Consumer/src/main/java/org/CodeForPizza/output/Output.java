package org.CodeForPizza.output;


import org.CodeForPizza.dto.MovieDTO;

public class Output {
    public static String printMenu() {
       return  """
         Welcome to the movie database. Please choose an option:
        1. Add a movie to the database
        2. list all movies in the database
        3. update a movie in the database
        4. delete a movie from the database
        5. exit program
        """;
    }

    public static String thankYou() {
        return  "Thank you for using the movie database. Goodbye.";

    }

    public static String askForYear() {
        return  "Please enter the year: ";
    }

    public static String askForTitle() {
        return  "Please enter the title: ";
    }

    public static String movieSaved() {
        return  "Movie information sent to the database.";
    }

    public static String movieDeleted() {
        return  "Movie deleted from the database.";
    }

    public static String MovieUpdated() {
        return  "Movie updated in the database.";
    }

    public static String whatMovieToDelete() {
        return  "What movie do you want to delete?";
    }

    public static String whatIdToDelete() {
        return "what is the id of the movie you want to delete?";
    }

    public static String whatMovieToUpdate() {
        return "What movie do you want to update?";
    }

    public static String whatIdToUpdate() {
        return "what is the id of the movie you want to update?";
    }

    public static String newTitle() {
       return  "what is the new title?";
    }

    public static String newYear() {
        return "what is the new year?";
    }

    public static String allMovies() {
        return "Here are all the movies in the database:";
    }

    public static String YearOutOfRange() {
        return "Year must be between 1888 and 2024. Please try again.";
    }

    public static String yearCantBeZero() {
        return "Year cant be 0. Please try again.";
    }

    public static String titleCantBeEmpty() {
        return "Title cant be empty. Please try again.";
    }

    public static String titleCantBeSwe() {
        return "Title cant contain å, ä or ö. Please try again.";
    }

    public static String movieInformation(MovieDTO movie) {
        return "Id: " + movie.getId()
                + " Title: " + movie.getTitle()
                + " Year: " + movie.getYear();
    }

    public static String breakBar() {
        return "=============================================";
    }

    public static String noMovieWithThatId() {
        return "There is no movie with that ID.";
    }
}
