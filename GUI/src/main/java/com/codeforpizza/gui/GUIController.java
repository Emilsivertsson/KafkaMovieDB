package com.codeforpizza.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GUIController {
    public Button saveB;
    public TextField titleF;
    public TextField yearF;
    public Label infoL;


    public void initialize() {

    }

    @FXML
    protected void onSaveButtonClick() {
        try {
            if (titleF.getText().isEmpty() || yearF.getText().isEmpty()) {
                infoL.setText("Please fill in all fields");
                return;
            }
            JSONObject movie = new JSONObject();
            movie.put("title", titleF.getText());
            movie.put("year", yearF.getText());

            executePostRequest(movie);
            infoL.setText("Movie saved!");



        } catch (Exception e) {
            infoL.setText("Error creating JSON");
        }

    }

    protected void executePostRequest(JSONObject movie) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/movie/save");
            httpPost.setHeader("Content-Type", "application/json; utf-8");

            // set the JSON object as the payload
            StringEntity entity = new StringEntity(movie.toJSONString());
            httpPost.setEntity(entity);

            // execute the POST request
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int responseCode = response.getStatusLine().getStatusCode();
                infoL.setText("Response Code: " + responseCode);
            } catch (Exception e) {
                infoL.setText("Error executing POST request");
            }
        } catch (Exception e) {
            infoL.setText("Error creating HTTP client");
        }
    }

}