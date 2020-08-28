package com.iplante.imdb.cast.repository;

import com.iplante.imdb.cast.entity.Cast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

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
     * Find all cast with IDs in a given list.
     *
     * @param castIds  the list of cast IDs.
     * @param pageable the pageable parameters.
     * @return a list of cast.
     */
    @RestResource(path = "ids")
    Page<Cast> findAllByIdIsIn(@Param("castIds") List<Long> castIds, Pageable pageable);

    /**
     * Find all cast matching a given first name.
     *
     * @param firstName the first name to find.
     * @param pageable the pageable parameters.
     * @return a list of cast matching the given first name.
     */
    @RestResource(path = "firstName")
    Page<Cast> findAllByFirstNameEquals(@Param("firstName") String firstName, Pageable pageable);

    /**
     * Find all cast matching a given last name.
     *
     * @param lastName the last name to find.
     * @param pageable the pageable parameters.
     * @return a list of cast matching the given last name.
     */
    @RestResource(path = "lastName")
    Page<Cast> findAllByLastNameEquals(@Param("lastName") String lastName, Pageable pageable);
}