package org.CodeForPizza;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MovieDBApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Please enter your movie title: ");
            String title = scanner.nextLine();
            System.out.print("Please enter the production year: ");
            String year = scanner.nextLine();

            // Construct JSON input string
            JSONObject movie = new JSONObject();
            movie.put("title", title);
            movie.put("year", year);
            System.out.println(movie.toJSONString());

            // Send POST request to API-Producer
            executePostRequest(movie);
        } catch (Exception e) {
            log.error("Error creating JSON");
        } finally {
            scanner.close();
        }
    }

    private static void executePostRequest(JSONObject movie) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/movie/save");
            httpPost.setHeader("Content-Type", "application/json; utf-8");

            // set the JSON object as the payload
            StringEntity entity = new StringEntity(movie.toJSONString());
            httpPost.setEntity(entity);

            // execute the POST request
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int responseCode = response.getStatusLine().getStatusCode();
                log.info("Response Code: " + responseCode);
            } catch (Exception e) {
                log.error("Error executing POST request");
            }
        } catch (Exception e) {
            log.error("Error creating HTTP client");
        }
    }

}

