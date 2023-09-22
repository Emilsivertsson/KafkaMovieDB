package org.CodeForPizza.application;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.connection.HttpConnection;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.output.Output;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class Application {

    HttpConnection httpConnection = new HttpConnection();

    MovieDTO movie = new MovieDTO();

    MovieDTO movieToUpdate = new MovieDTO();

    List<MovieDTO> moviesFromDB;

    Boolean containsId = false;

    int id;
    Scanner scanner = new Scanner(System.in);

    public Application() {
        try {
            run();
        } catch (Exception e) {
            log.error("Error running application");
            log.error(e.getMessage());
        }
    }

    public void run() throws InterruptedException {
        while (true) {
        System.out.println(Output.printMenu());
        int input = Integer.parseInt(scanner.nextLine());
        switch (input){
            case 1: addMovie();
                break;
            case 2: listMovies();
                break;
            case 3: updateMovie();
                break;
            case 4: deleteMovie();
                break;
            case 5:
                System.out.println(Output.thankYou());
                System.exit(0);
                break;
            default: System.out.println("Invalid input. Please try again.");
        }
    }
}

    private  void deleteMovie() {
        System.out.println(Output.whatMovieToDelete());
        moviesFromDB = httpConnection.getAllMovies();
        for (MovieDTO movie : moviesFromDB) {
            System.out.println(Output.movieInformation(movie));
        }
        System.out.println(Output.breakBar());
        System.out.println(Output.whatIdToDelete());
        id = Integer.parseInt(scanner.nextLine());
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
            System.out.println(Output.noMovieWithThatId());
        }
    }

    private void updateMovie() {
        System.out.println(Output.whatMovieToUpdate());
        moviesFromDB = httpConnection.getAllMovies();
        for (MovieDTO movie : moviesFromDB) {
            System.out.println("Id: " + movie.getId()
                    + " Title: " + movie.getTitle()
                    + " Year: " + movie.getYear());
        }
        System.out.println(Output.breakBar());
        System.out.println(Output.whatIdToUpdate());
        id = Integer.parseInt(scanner.nextLine());
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
            System.out.println(Output.newTitle());
            String newTitle = scanner.nextLine();
            System.out.println(Output.newYear());
            int newYear = Integer.parseInt(scanner.nextLine());
            movieToUpdate.setTitle(newTitle);
            movieToUpdate.setYear(String.valueOf(newYear));
            System.out.println(httpConnection.updateMovie(id, movieToUpdate));
        } else {
            System.out.println(Output.noMovieWithThatId());
        }
    }

    private  void listMovies() {
        System.out.println(Output.allMovies());
        moviesFromDB = httpConnection.getAllMovies();
        for (MovieDTO movie : moviesFromDB) {
            System.out.println(Output.movieInformation(movie));

        }
        System.out.println(Output.breakBar());
    }

    private  void addMovie() {
        try{
        createMovie();
        System.out.println(httpConnection.saveMovieToAPI(movie));
        Thread.sleep(2000);
        } catch (Exception e) {
            log.error("Error adding movie");
            log.error(e.getMessage());
        }
    }

    private void createMovie( ) {
        try  {
            movie.setTitle(askForTitle());
            movie.setYear(askForYear());
        } catch (Exception e) {
            log.error("Error creating Movie");
            log.error(e.getMessage());
        }
    }

    private  String askForYear( ) {
        int year;
        try{
            do {
                System.out.println(Output.askForYear());
                year = Integer.parseInt(scanner.nextLine());
                if (year < 1888 || year > 2024) {
                    System.out.println(Output.YearOutOfRange());
                }
                if (year <= 0 ) {
                    System.out.println(Output.yearCantBeZero());
                }
            } while (year < 1888 || year > 2024 || year <= 0);
            return String.valueOf(year);
        } catch (Exception e) {
            log.error("Error asking for year");
            return e.getMessage();
        }
    }

    private  String askForTitle() {
        String title;
        try{
            do {
                System.out.println(Output.askForTitle());
                title = scanner.nextLine();
                if(title.isEmpty()){
                    System.out.println(Output.titleCantBeEmpty());
                }
                if (title.contains("å") || title.contains("ä") || title.contains("ö")) {
                    System.out.println(Output.titleCantBeSwe());
                }
            } while (title.contains("å") || title.contains("ä") || title.contains("ö") || title.isEmpty() || title.isBlank());
            return title;
        } catch (Exception e) {
            log.error("Error asking for title");
            return e.getMessage();
        }
    }
}
