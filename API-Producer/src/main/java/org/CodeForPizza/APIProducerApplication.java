package org.CodeForPizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class APIProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIProducerApplication.class);

    }
}