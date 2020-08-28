package com.iplante.imdb.cast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CastApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CastApiApplication.class, args);
    }

}
