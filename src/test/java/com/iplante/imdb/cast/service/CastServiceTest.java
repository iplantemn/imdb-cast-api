package com.iplante.imdb.cast.service;

import com.iplante.imdb.cast.entity.Cast;
import com.iplante.imdb.cast.repository.CastRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CastService}
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class CastServiceTest {

    private final static Long CAST_ID = 1L;

    @InjectMocks
    private CastService castService;

    @Mock
    private CastRepository castRepository;

    @Mock
    private MovieClient movieClient;

    @Test
    void addCastSavesToRepository() {
        final var castList = Arrays.asList(
                Cast.builder().firstName("Jane").lastName("Doe").build(),
                Cast.builder().firstName("John").lastName("Smith").build()
        );

        castService.addCast(castList);
        verify(castRepository).saveAll(eq(castList));
    }

    @Test
    void getCastCountsReturnsTableCount() {
        when(castRepository.count()).thenReturn(100L);
        assertThat(castService.getCastCount()).isEqualTo(100L);
        verify(castRepository).count();
    }

    @Test
    void getCastMoviesThrowsWhenMovieNotExists() {
        when(castRepository.findById(CAST_ID)).thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> castService.getCastMovies(CAST_ID, null)
        );
        verify(movieClient, never()).getCastMovies(eq(CAST_ID), isNull());
    }

    @Test
    void getCastMoviesFetchesFromMoviesApi() {
        when(castRepository.findById(CAST_ID)).thenReturn(Optional.of(Cast.builder().id(CAST_ID).build()));
        castService.getCastMovies(CAST_ID, null);
        verify(movieClient).getCastMovies(eq(CAST_ID), isNull());
    }
}
