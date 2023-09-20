package org.CodeForPizza.connection;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.Movie;
import org.CodeForPizza.output.Output;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a HTTP client and sends a POST request to the API.
 * It uses the JSONObject movie to send the movie information to the API.
 */
@Slf4j
public class HttpConnection {

    public void sendRequestToAPI(JSONObject movie) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/movie/save");
            httpPost.setHeader("Content-Type", "application/json; utf-8");
            StringEntity entity = new StringEntity(movie.toJSONString());
            httpPost.setEntity(entity);
            executePOST(httpClient, httpPost);

        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            log.info(e.getMessage());
        }
    }

    public void executePOST(CloseableHttpClient httpClient, HttpPost httpPost) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            Output.printResponse();

        } catch (Exception e) {
            log.error("Error executing POST request, please check that you have the API running.");
            log.info(e.getMessage());
        }
    }

    public List<Movie> getAllMovies() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/api/v1/movie/all");
            httpGet.setHeader("Content-Type", "application/json; utf-8");
            return executeGET(httpClient, httpGet);

        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            log.info(e.getMessage());
        }
        return null;
    }

    private List<Movie> executeGET(CloseableHttpClient httpClient, HttpGet httpGet) {
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            if (responseCode == 200) {
                // Parse the JSON response and convert it into a list of Movie objects
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseText.append(line);
                }

                // Parse the JSON array into a list of Movie objects
                List<Movie> movieList = new ArrayList<>();
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = (JSONArray) jsonParser.parse(responseText.toString());
                for (Object obj : jsonArray) {
                    JSONObject movieJson = (JSONObject) obj;
                    String title = (String) movieJson.get("title");
                    String year = (String) movieJson.get("year");
                    // Assuming you have a Movie constructor that accepts title and year
                    Movie movie = new Movie(title, year);
                    movieList.add(movie);
                }

                return movieList;
            } else {
                log.error("Error: Unexpected response code - " + responseCode);
            }
        } catch (Exception e) {
            log.error("Error executing GET request, please check that you have the API running.");
            log.info(e.getMessage());
        }
        return null;


    }
}