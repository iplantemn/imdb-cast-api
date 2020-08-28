package com.iplante.imdb.cast.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration object for the Movie API.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "movies-api")
public class MovieApiConfiguration {

    /**
     * The base URL of the Movies API.
     */
    private String baseUrl;
}
