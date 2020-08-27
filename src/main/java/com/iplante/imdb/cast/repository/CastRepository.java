package com.iplante.imdb.cast.repository;

import com.iplante.imdb.cast.entity.Cast;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Repository for {@link Cast} entities, additionally exposing CRUD operations as pageable and
 * sortable RESTful API.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/24/20
 */
@RepositoryRestResource(collectionResourceRel = "cast", path = "cast")
public interface CastRepository extends PagingAndSortingRepository<Cast, Long> {

    /**
     * Find all cast matching a given first name.
     *
     * @param firstName the first name to find.
     * @return a list of cast matching the given first name.
     */
    List<Cast> findAllByFirstNameEquals(@Param("firstName") String firstName);

    /**
     * Find all cast matching a given last name.
     *
     * @param lastName the last name to find.
     * @return a list of cast matching the given last name.
     */
    List<Cast> findAllByLastNameEquals(@Param("lastName") String lastName);
}