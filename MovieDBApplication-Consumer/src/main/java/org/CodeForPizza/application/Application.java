package org.CodeForPizza.application;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.Input.Inputs;
import org.CodeForPizza.connection.HttpConnection;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.output.Output;

import java.util.List;

/**
 * This class is the application itself, it lets the user preform CRUD operations on the database via the API.
 * although the save function goes throu kafka as per the assignment.
 */

@Slf4j
public class Application {

    private HttpConnection httpConnection = new HttpConnection();

    private Inputs inputs = new Inputs();

    private MovieDTO movie = new MovieDTO();

    private MovieDTO movieToUpdate = new MovieDTO();

    private List<MovieDTO> moviesFromDB;

    private Boolean containsId = false;

    private int id;


    public void run() {
        while (true) {
            int input = inputs.askForInt(Output.printMenu);
            switch (input) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    listMovies();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 5:
                    System.out.println(Output.thankYou);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * all the metods basically do the same thing, they check if there are movies in the database, if there arent they return.
     * if there are movies they list them and then ask for an id, if the id exists they preform the action.
     */
    private void deleteMovie() {
        if (isThereMovies()) {
            return;
        }
        listMovies();
        System.out.println(Output.whatMovieToDelete);
        id = inputs.askForInt(Output.whatIdToDelete);
        ifIdExistsDelete();
    }

    private void ifIdExistsDelete() {
        for (MovieDTO movie : moviesFromDB) {
            if (movie.getId() == id) {
                containsId = true;
                break;
            }
        }
        if (containsId) {
            System.out.println(httpConnection.deleteMovie(id));
        } else {
            System.out.println(Output.noMovieWithThatId);
        }
    }

    private void updateMovie() {
        if (isThereMovies()) {
            return;
        }
        listMovies();
        System.out.println(Output.whatMovieToUpdate);
        id = inputs.askForInt(Output.whatIdToUpdate);
        ifIdExistsUpdate();
    }

    private void ifIdExistsUpdate() {
        for (MovieDTO movie : moviesFromDB) {
            if (movie.getId() == id) {
                containsId = true;
                break;
            }
        }
        if (containsId) {
            changePropertiesForMovie();
        } else {
            System.out.println(Output.noMovieWithThatId);
        }
    }

    private void changePropertiesForMovie() {
        String newTitle = inputs.askForString(Output.newTitle);
        String newYear = inputs.askForIntAsString(Output.newYear);
        movieToUpdate.setTitle(newTitle);
        movieToUpdate.setYear(String.valueOf(newYear));
        System.out.println(httpConnection.updateMovie(id, movieToUpdate));
    }

    private void listMovies() {
        if (isThereMovies()) {
            return;
        }
        System.out.println(Output.allMovies);
        for (MovieDTO movie : moviesFromDB) {
            System.out.println(Output.movieInformation(movie));
        }
        System.out.println(Output.breakBar);

    }

    private boolean isThereMovies() {
        moviesFromDB = httpConnection.getAllMovies();
        if (moviesFromDB.isEmpty()) {
            System.out.println(Output.noMoviesInDB);
            return true;
        }
        return false;
    }

    /**
     * this method is the only one that doesnt use the API, it uses kafka to send the movie to the database.
     * it uses the method createMovie to create a movie object and sends is to kafka.
     * it then sleeps for 2 seconds to give kafka time to send and let the consumer recive the message.
     */
    private void addMovie() {
        try {
            createMovie();
            System.out.println(httpConnection.saveMovieToAPI(movie));
            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("Error adding movie");
            log.error(e.getMessage());
        }
    }

    private void createMovie() {
        try {
            movie.setTitle(inputs.askForString(Output.askForTitle));
            movie.setYear(inputs.askForIntAsString(Output.askForYear));
        } catch (Exception e) {
            log.error("Error creating Movie");
            log.error(e.getMessage());
        }
    }


}
