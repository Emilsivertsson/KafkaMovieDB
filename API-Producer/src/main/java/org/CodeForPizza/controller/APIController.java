package org.CodeForPizza.controller;

import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
public class APIController {

        private KafkaProducer kafkaProducer;

        public APIController(KafkaProducer kafkaProducer) {
            this.kafkaProducer = kafkaProducer;
        }


        // http://localhost:8080/api/v1/movie/save?title=HelloWorld&year=2021
        @PostMapping("/save")
        public ResponseEntity<String> publish(@RequestBody() Movie movie) {
            try{
            kafkaProducer.sendMessage(movie);
            return ResponseEntity.ok().body("Message sent to Topic");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error sending message to Topic");
            }
        }



}
