package org.CodeForPizza.connection;

import lombok.extern.slf4j.Slf4j;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.output.Output;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 * This class creates a HTTP client and sends a POST request to the API.
 * It uses the JSONObject movie to send the movie information to the API.
 */
@Slf4j
public class HttpConnection {

    Gson gson = new Gson();

    public  MovieDTO getMovieById(int id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/api/v1/movie/" + id);
            httpGet.setHeader("Content-Type", "application/json; utf-8");
            return executeGETOne(httpClient, httpGet);
        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            log.error(e.getMessage());
            return null;
        }
    }

    private MovieDTO executeGETOne(CloseableHttpClient httpClient, HttpGet httpGet) {
        try{
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseText.append(line);
                }
                String movieAsJson = responseText.toString();
                MovieDTO movieToUpdate = gson.fromJson(movieAsJson, MovieDTO.class);
                return movieToUpdate;
            } else {
                log.error("Error: Unexpected response code - " + responseCode);
                return null;
            }
        } catch (Exception e) {
            log.error("Error executing GET request, please check that you have the API running.");
            log.error(e.getMessage());
            return null;
        }
    }

    public void saveMovieToAPI(MovieDTO movie) {
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/movie/save");
            httpPost.setHeader("Content-Type", "application/json; utf-8");
            String movieAsJson = gson.toJson(movie);
            httpPost.setEntity(new StringEntity(movieAsJson));
            executePOST(httpClient, httpPost);
        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            log.error(e.getMessage());
        }
    }

    public void executePOST(CloseableHttpClient httpClient, HttpPost httpPost) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int responseCode = response.getStatusLine().getStatusCode();
            log.error("Response Code: " + responseCode);
            Output.printResponse();

        } catch (Exception e) {
            log.error("Error executing POST request, please check that you have the API running.");
            log.error(e.getMessage());
        }
    }

    public List<MovieDTO> getAllMovies() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/api/v1/movie/all");
            httpGet.setHeader("Content-Type", "application/json; utf-8");
            return executeGETAll(httpClient, httpGet);

        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            return null;
        }
    }

    private List<MovieDTO> executeGETAll(CloseableHttpClient httpClient, HttpGet httpGet) {
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseText.append(line);
                }

                List<MovieDTO> movieList = new ArrayList<>();
                String movieListAsJson = responseText.toString();
                MovieDTO[] movieArray = gson.fromJson(movieListAsJson, MovieDTO[].class);
                for (MovieDTO movie : movieArray) {
                    movieList.add(movie);
                }

                return movieList;
            } else {
                log.error("Error: Unexpected response code - " + responseCode);
                return null;
            }
        } catch (Exception e) {
            log.error("Error executing GET request, please check that you have the API running.");
            return null;
        }
    }


    public String deleteMovie(int id) {
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete("http://localhost:8080/api/v1/movie/delete/" + id);
            httpDelete.setHeader("Content-Type", "application/json; utf-8");
            return executeDELETE(httpClient, httpDelete);
        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            return e.getMessage();
        }
    }

    private String executeDELETE(CloseableHttpClient httpClient, HttpDelete httpDelete) {
        try {
            CloseableHttpResponse response = httpClient.execute(httpDelete);
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            if (responseCode == 200) {
                 return Output.movieDeleted();
            } else {
                return "Error: Unexpected response code - " + responseCode;
            }
        } catch (Exception e) {
            log.error("Error executing DELETE request, please check that you have the API running.");
            return e.getMessage();
        }
    }

    public String updateMovie(int id, MovieDTO movie) {
        //http://localhost:8080/api/v1/movie/update/{id}
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut("http://localhost:8080/api/v1/movie/update/" + id);
            httpPut.setHeader("Content-Type", "application/json; utf-8");
            String movieAsJson = gson.toJson(movie);
            httpPut.setEntity(new StringEntity(movieAsJson));
            return executePut(httpClient, httpPut);
        } catch (Exception e) {
            log.error("Error creating HTTP client, please check that your API is running.");
            return e.getMessage();
        }
    }

    private String executePut(CloseableHttpClient httpClient, HttpPut httpPut) {
        try{
            CloseableHttpResponse response = httpClient.execute(httpPut);
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("Response Code: " + responseCode);
            if (responseCode == 200) {
                return Output.MovieUpdated();
            } else {
                return  "Error: Unexpected response code - " + responseCode;
            }
        } catch (Exception e) {
            log.error("Error executing PUT request, please check that you have the API running.");
            return e.getMessage();
        }
    }
}