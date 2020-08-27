package com.iplante.imdb.cast.service;

import com.iplante.imdb.cast.entity.Cast;
import com.iplante.imdb.cast.repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service in charge of custom CRUD operations against {@link Cast} entities.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
@Service
public class CastService {

    private final CastRepository castRepository;

    private final MovieClient movieClient;

    /**
     * Service constructor.
     *
     * @param castRepository the {@link Cast} repository.
     * @param movieClient the REST client to interact with the Movies API.
     */
    @Autowired
    public CastService(CastRepository castRepository, MovieClient movieClient) {
        this.castRepository = castRepository;
        this.movieClient = movieClient;
    }

    /**
     * Add a list of {@link Cast} entities to the database.
     *
     * @param castList the list of {@link Cast} entities.
     */
    public void addCast(List<Cast> castList) {
        castRepository.saveAll(castList);
    }

    /**
     * Get a count of all {@link Cast} entities in the database.
     *
     * @return a count of {@link Cast} entities.
     */
    public long getCastCount() {
        return castRepository.count();
    }

    /**
     * Get all movies in which a given {@link Cast} is credited.
     *
     * @param castId the ID of the {@link Cast}
     * @param pageable the optional Pageable query parameters.
     * @return Object containing a list of movies in which the given {@link Cast} is credited. We return Object to just
     *      act as a proxy and return exactly what we got.
     * @throws EntityNotFoundException if no {@link Cast} found for given ID.
     */
    public Object getCastMovies(long castId, Pageable pageable) {
        final var cast = castRepository.findById(castId);

        if (cast.isPresent()) {
            return movieClient.getCastMovies(castId, pageable);
        } else {
            throw new EntityNotFoundException("Cast member not found: castId=" + castId);
        }
    }
}
