package org.CodeForPizza.output;


import org.CodeForPizza.dto.MovieDTO;

public class Output {

    public static String printMenu = """
            Welcome to the movie database. Please choose an option:
            1. Add a movie to the database
            2. list all movies in the database
            3. update a movie in the database
            4. delete a movie from the database
            5. exit program
            """;
    public static String thankYou = "Thank you for using the movie database. Goodbye.";

    public static String askForYear = "Please enter the year: ";
    public static String askForTitle = "Please enter the title: ";

    public static String movieSaved = "Movie information sent to the database.";

    public static String movieDeleted = "Movie deleted from the database.";

    public static String MovieUpdated = "Movie updated in the database.";

    public static String whatMovieToDelete = "What movie do you want to delete?";

    public static String whatIdToDelete = "what is the id of the movie you want to delete?";

    public static String whatMovieToUpdate = "What movie do you want to update?";

    public static String whatIdToUpdate = "what is the id of the movie you want to update?";

    public static String newTitle = "what is the new title?";

    public static String newYear = "what is the new year?";

    public static String allMovies = "Here are all the movies in the database:";

    public static String YearOutOfRange = "Year must be between 1888 and 2024. Please try again.";

    public static String yearCantBeZero = "Year cant be 0. Please try again.";

    public static String titleCantBeEmpty = "Title cant be empty. Please try again.";

    public static String titleCantBeSwe = "Title cant contain å, ä or ö. Please try again.";

    public static String breakBar = "=============================================";

    public static String noMovieWithThatId = "There is no movie with that ID.";

    public static String movieInformation(MovieDTO movie) {
        return "Id: " + movie.getId()
                + " Title: " + movie.getTitle()
                + " Year: " + movie.getYear();
    }
}
