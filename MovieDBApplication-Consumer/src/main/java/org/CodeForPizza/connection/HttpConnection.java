package org.CodeForPizza.connection;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

/**
 * This class creates a HTTP client and sends a POST request to the API.
 * It uses the JSONObject movie to send the movie information to the API.
 */
@Slf4j
public class HttpConnection {

    public void sendRequestToAPI(JSONObject movie)   {
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
            System.out.println("Movie information sent to the database.");

        } catch (Exception e) {
            log.error("Error executing POST request, please check that you have the API running.");
            log.info(e.getMessage());
        }
    }
}
