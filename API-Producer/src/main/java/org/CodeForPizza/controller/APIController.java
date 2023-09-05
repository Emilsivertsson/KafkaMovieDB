package org.CodeForPizza.controller;


import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.producer.KafkaProducer;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/movie")
public class APIController {

        private KafkaProducer kafkaProducer;

        public APIController(KafkaProducer kafkaProducer) {
            this.kafkaProducer = kafkaProducer;
        }


        // http://localhost:8080/api/v1/movie/save
        @PostMapping("/save")
        public ResponseEntity<String> publish(@RequestBody() String movie) {
            try{
                log.info("Received message: " + movie);
            kafkaProducer.sendMessage(movie);
            return ResponseEntity.ok().body("Message sent to Topic");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error sending message to Topic");

            }
        }



}
