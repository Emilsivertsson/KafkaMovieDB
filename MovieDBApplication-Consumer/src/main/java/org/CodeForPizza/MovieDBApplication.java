package org.CodeForPizza;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import java.util.Scanner;

/**
 * this class takes user input and turns it into a JSON object.
 * It sends the JSON object as a string, to the API as a POST request.
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MovieDBApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MovieDBApplication.class);

        JSONObject movieToSave = new JSONObject();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter '1' to save a movie to the database.");
            System.out.println("Enter '2' to exit the program.");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                createJson(scanner, movieToSave);
                sendRequestToAPI(movieToSave);
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
        if(year.equals("") || year.length() != 4){
            System.out.println("Year cant be empty and must be 4 digits. Please try again.");
            askForYear(scanner);
        }
        return year;
    }

    private static String askForTitle(Scanner scanner) {
        System.out.print("Please enter your movie title: ");
        String title = scanner.nextLine();
        if(title.equals("")) {
            System.out.println("Title cant be empty. Please try again.");
            askForTitle(scanner);
        }
        return title;
    }

    private static void sendRequestToAPI(JSONObject movie)   {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/movie/save");
            httpPost.setHeader("Content-Type", "application/json; utf-8");

            // set the JSON object as the payload
            StringEntity entity = new StringEntity(movie.toJSONString());
            httpPost.setEntity(entity);

            // execute the POST request
            executePOST(httpClient, httpPost);

        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            log.info(e.getMessage());
        }
    }

    private static void executePOST(CloseableHttpClient httpClient, HttpPost httpPost) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            System.out.println("Movie information sent to the database.");

        } catch (Exception e) {
            log.error("Error executing POST request, please check that you have the API running.");
            log.info(e.getMessage());
        }
    }

}

