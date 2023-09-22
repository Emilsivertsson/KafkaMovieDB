package org.CodeForPizza;


import org.CodeForPizza.application.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * this class takes user input and turns it into a JSON object and sends it to the httpConnection class.
 *
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MovieDBApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MovieDBApplication.class);
        Application application = new Application();
    }
}


