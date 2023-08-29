package org.CodeForPizza;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * this class is used to send a POST request to the API-Producer
 * it constructs a JSON object from user input and sends it to the API-Producer
 */
public class MovieDBApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Please enter your movie title: ");
            String title = scanner.nextLine();
            System.out.println("Please enter the production year: ");
            int year = scanner.nextInt();


            String jsonInputString = "{\"title\": \"" +title + "\", \"year\": \""+year+"\"}";

            //creates a connection to the API-Producer and sets the request method to POST
            //it also sets the content type to JSON and enables output to the connection
            URL url = new URL("http://localhost:8080/api/v1/movie/save");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            //writes the JSON object to the connection output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

            } catch (Exception e) {
                System.out.println("Error writing Json data: " + e.getMessage());
            }
            System.out.println(conn.getResponseCode());

            conn.disconnect();
            scanner.close();



        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}