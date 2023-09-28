package org.CodeForPizza.application;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.Input.Inputs;
import org.CodeForPizza.connection.HttpConnection;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.output.Output;

import java.util.List;

@Slf4j
public class Application {

    HttpConnection httpConnection = new HttpConnection();

    Inputs inputs = new Inputs();

    MovieDTO movie = new MovieDTO();

    MovieDTO movieToUpdate = new MovieDTO();

    List<MovieDTO> moviesFromDB;

    Boolean containsId = false;

    Boolean isEmpty = false;

    int id;


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
