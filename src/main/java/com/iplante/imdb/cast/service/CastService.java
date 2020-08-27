package com.iplante.imdb.cast.service;

import com.iplante.imdb.cast.entity.Cast;
import com.iplante.imdb.cast.repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    private static final String MOVIES_API_URL = "http://localhost:5012";

    private final CastRepository castRepository;

    /**
     * Service constructor.
     *
     * @param castRepository the {@link Cast} repository.
     */
    @Autowired
    public CastService(CastRepository castRepository) {
        this.castRepository = castRepository;
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
     * todo: automatically unmarshall JSON response.
     *
     * @param castId the ID of the {@link Cast}
     * @return a list of movies in which the given {@link Cast} is credited.
     * @throws EntityNotFoundException if no {@link Cast} found for given ID.
     */
    public String getCastMovies(long castId) {
        final var cast = castRepository.findById(castId);

        if (cast.isPresent()) {
            final var uri = String.format("/api/v1/movies?castId=%d&size=%d", castId, Integer.MAX_VALUE);
            return getMoviesApiWebClient()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } else {
            throw new EntityNotFoundException("Cast member not found: castId=" + castId);
        }
    }

    /**
     * Get the {@link WebClient} to make REST calls against the Movies API.
     *
     * @return the {@link WebClient}.
     */
    private WebClient getMoviesApiWebClient() {
        return WebClient
                .builder()
                .baseUrl(MOVIES_API_URL)
                .build();
    }
}
