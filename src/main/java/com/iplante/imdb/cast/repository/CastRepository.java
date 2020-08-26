package com.iplante.imdb.cast.repository;

import com.iplante.imdb.cast.entity.Cast;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
}