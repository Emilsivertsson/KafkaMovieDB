package org.CodeForPizza.application;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.connection.HttpConnection;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.output.Output;

import java.util.Scanner;

@Slf4j
public class Application {

    HttpConnection httpConnection = new HttpConnection();

    MovieDTO movie = new MovieDTO();
    Scanner scanner = new Scanner(System.in);

    public Application() {
        try {
            run();
        } catch (Exception e) {
            log.error("Error running application");
            log.info(e.getMessage());
        }
    }

    public void run() throws InterruptedException {
        while (true) {
        Output.printMenu();
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
            case 5: Output.thankYou();
                System.exit(0);
                break;
            default: System.out.println("Invalid input. Please try again.");
        }
    }
}

    private  void deleteMovie() {
    }

    private  void updateMovie() {
    }

    private  void listMovies() {
    }

    private  void addMovie() {
        createMovie();
        System.out.println(movie.toString());
        httpConnection.sendRequestToAPI(movie);
    }

    private void createMovie( ) {
        try  {
            movie.setTitle(askForTitle());
            movie.setYear(askForYear());

        } catch (Exception e) {
            log.error("Error creating Movie");
            log.info(e.getMessage());
        }
    }

    private  String askForYear( ) {
        int year;
        do {
            Output.askForYear();
            year = Integer.parseInt(scanner.nextLine());
            if (year < 1888 || year > 2024) {
                System.out.println("Year must be between 1888 and 2024. Please try again.");
            }
            if (year <= 0 ) {
                System.out.println("Year cant be 0. Please try again.");
            }
        } while (year < 1888 || year > 2024 || year <= 0);
        return String.valueOf(year);
    }

    private  String askForTitle() {
        String title;
        do {
            Output.askForTitle();
            title = scanner.nextLine();
            if(title.isEmpty()){
                System.out.println("Title cant be empty. Please try again.");
            }
            if (title.contains("å") || title.contains("ä") || title.contains("ö")) {
                System.out.println("Title cant contain å, ä or ö. Please try again.");
            }
        } while (title.contains("å") || title.contains("ä") || title.contains("ö") || title.isEmpty() || title.isBlank());

        return title;
    }

}
