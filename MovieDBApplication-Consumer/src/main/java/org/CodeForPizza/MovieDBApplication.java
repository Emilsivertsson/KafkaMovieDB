package org.CodeForPizza;


import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.connection.HttpConnection;
import org.CodeForPizza.dto.Movie;
import org.CodeForPizza.output.Output;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;
import java.util.Scanner;

/**
 * this class takes user input and turns it into a JSON object and sends it to the httpConnection class.
 *
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MovieDBApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MovieDBApplication.class);

        HttpConnection httpConnection = new HttpConnection();

        JSONObject movieToSave = new JSONObject();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Output.printMenu();
            int input = Integer.parseInt(scanner.nextLine());
            switch (input){
                case 1: addMovie(scanner, httpConnection, movieToSave);
                        break;
                case 2: listMovies(scanner, httpConnection);
                        break;
                case 3: updateMovie(scanner, httpConnection, movieToSave);
                        break;
                case 4: deleteMovie(scanner, httpConnection);
                        break;
                case 5: Output.thankYou();
                        System.exit(0);
                        break;
                default: System.out.println("Invalid input. Please try again.");
            }

        }
    }

    private static void deleteMovie(Scanner scanner, HttpConnection httpConnection) {
    }

    private static void updateMovie(Scanner scanner, HttpConnection httpConnection, JSONObject movieToSave) {
    }

    private static void listMovies(Scanner scanner, HttpConnection httpConnection) {
        System.out.println("Listing all movies in database...Awaiting response from server.");
        List<Movie> movies = httpConnection.getAllMovies();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private static void addMovie(Scanner scanner, HttpConnection httpConnection, JSONObject movieToSave) throws InterruptedException {
        createJson(scanner, movieToSave);
        httpConnection.sendRequestToAPI(movieToSave);
        System.out.println("Saving movie to database...Awaiting response from server.");
        Thread.sleep(3000);
    }

    private static void createJson(Scanner scanner, JSONObject movieToSave) {
        try  {
            String title = askForTitle(scanner);
            String year = Integer.toString(askForYear(scanner));
            movieToSave.put("title", title);
            movieToSave.put("year", year);
        } catch (Exception e) {
            log.error("Error creating JSON");
            log.info(e.getMessage());
        }
    }

    private static int askForYear(Scanner scanner) {
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
        return year;
    }

    private static String askForTitle(Scanner scanner) {
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


