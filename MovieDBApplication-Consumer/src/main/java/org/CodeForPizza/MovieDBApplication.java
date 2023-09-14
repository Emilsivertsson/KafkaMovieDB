package org.CodeForPizza;


import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.connection.HttpConnection;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
            System.out.println("Enter '1' to save a movie to the database.");
            System.out.println("Enter '2' to exit the program.");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                createJson(scanner, movieToSave);
                httpConnection.sendRequestToAPI(movieToSave);
                System.out.println("Saving movie to database...");
                Thread.sleep(2000);
            } else if (input.equals("2")) {
                System.out.println("Exiting program.");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void createJson(Scanner scanner, JSONObject movieToSave) {
        try  {
            String title = askForTitle(scanner);
            String year = askForYear(scanner);
            movieToSave.put("title", title);
            movieToSave.put("year", year);
        } catch (Exception e) {
            log.error("Error creating JSON");
            log.info(e.getMessage());
        }
    }

    private static String askForYear(Scanner scanner) {
        System.out.print("Please enter the production year: ");
        String year = scanner.nextLine();
        if(year.length() != 4){
            System.out.println("Year cant be empty and must be 4 digits. Please try again.");
            askForYear(scanner);
        }
        return year;
    }

    private static String askForTitle(Scanner scanner) {
        System.out.print("Please enter your movie title: ");
        String title = scanner.nextLine();
        if(title.isEmpty()) {
            System.out.println("Title cant be empty. Please try again.");
            askForTitle(scanner);
        }
        return title;
    }



}


