package org.CodeForPizza;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Scanner;

@Slf4j
public class MovieDBApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Please enter your movie title: ");
            String title = scanner.nextLine();
            System.out.print("Please enter the production year: ");
            int year = scanner.nextInt();

            // Construct JSON input string
            String jsonInputString = "{\"title\": \"" + title + "\", \"year\": \"" + year + "\"}";

            // Send POST request to API-Producer and set content type to JSON
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/movie/save");
                httpPost.setHeader("Content-Type", "application/json; utf-8");

                // set the JSON input string as the payload
                StringEntity stringEntity = new StringEntity(jsonInputString);
                httpPost.setEntity(stringEntity);

                // execute the POST request
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    int responseCode = response.getStatusLine().getStatusCode();
                    log.info("Response Code: " + responseCode);
                } catch (Exception e) {
                    log.error("Error executing POST request");
                }
            }
        } catch (Exception e) {
            log.error("Error parsing input");
        }
    }

}

