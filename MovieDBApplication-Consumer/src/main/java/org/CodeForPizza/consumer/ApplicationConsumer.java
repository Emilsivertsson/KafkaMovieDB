package org.CodeForPizza.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;


/**
 * This class implements a kafka consumer that listens to the topic "movie", group "user".
 * It uses a KafkaListener to listen to the topic.
 * it prints out the movie information to the console.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Service
public class ApplicationConsumer {

    MovieDTO movieFromDataBase = new MovieDTO();

    Gson gson = new Gson();

    @KafkaListener(topics = "movie", groupId = "user")
    public void consume(MovieDTO movieInfo) {
        try {
            log.info("Consumed message: " + movieInfo);
            String movieInfoString = gson.toJson(movieInfo);
            movieFromDataBase = gson.fromJson(movieInfoString, MovieDTO.class);

            System.out.println("=============================================");
            System.out.println("Movie information saved in the database:");
            System.out.println("Title: " + movieFromDataBase.getTitle());
            System.out.println("Year: " + movieFromDataBase.getYear());
        } catch (Exception e) {
            log.error("Error parsing message: " + movieInfo.toString());
            throw new NullPointerException("Error parsing message: " + movieInfo);
        }


    }
}
