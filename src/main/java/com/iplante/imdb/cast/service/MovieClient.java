package com.iplante.imdb.cast.service;

import com.iplante.imdb.cast.entity.Cast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Client for the Movies API.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@Component
public class MovieClient {

    private static final String MOVIES_API_URL = "http://localhost:5012/api/v1/movies/search/cast";

    private final RestTemplate restTemplate;

    /**
     * Client constructor.
     *
     * @param restTemplate custom {@link RestTemplate}.
     */
    @Autowired
    public MovieClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Make an API call to Movies API to get a list of movies for a given {@link Cast}.
     *
     * @param castId   the ID of the cast to find.
     * @param pageable pageable attributes.
     * @return Object - so we can just be a pass-through and return the data as-is.
     */
    public Object getCastMovies(long castId, Pageable pageable) {
        final var uri = UriComponentsBuilder
                .fromUriString(MOVIES_API_URL)
                .queryParam("castId", castId)
                .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber())
                .toUriString();

        final var response = restTemplate.getForEntity(uri, Object.class);
        return response.getBody();
    }
}
