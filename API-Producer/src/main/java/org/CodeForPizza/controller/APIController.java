package org.CodeForPizza.controller;

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

        @GetMapping("/save")
        public ResponseEntity<String> publish(@RequestBody(Movie movie){
            kafkaProducer.sendMessage(movie);
            return ResponseEntity.ok("Movie sent to Topic");
        }


}
