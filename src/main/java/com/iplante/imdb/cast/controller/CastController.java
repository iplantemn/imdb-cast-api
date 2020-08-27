package com.iplante.imdb.cast.controller;

import com.iplante.imdb.cast.entity.Cast;
import com.iplante.imdb.cast.service.CastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller exposing custom CRUD operations on {@link Cast} that Spring Data REST cannot handle.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
@RepositoryRestController
public class CastController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CastController.class);

    private final CastService castService;

    /**
     * Controller constructor.
     *
     * @param castService the service in charge of business logic for {@link Cast} CRUD operations.
     */
    public CastController(CastService castService) {
        this.castService = castService;
    }

    /**
     * Get all movies in which a given {@link Cast} is credited.
     *
     * @param castId the ID of the {@link Cast}
     * @param pageable the optional Pageable query parameters.
     * @return a list of movies in which the given {@link Cast} is credited.
     */
    @GetMapping(path = "cast/{castId}/movies")
    public ResponseEntity<Object> getCastMovies(@PathVariable long castId, Pageable pageable) {
        return ResponseEntity.ok(castService.getCastMovies(castId, pageable));
    }
}
