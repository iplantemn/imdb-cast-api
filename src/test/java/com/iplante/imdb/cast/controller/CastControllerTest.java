package com.iplante.imdb.cast.controller;

import com.iplante.imdb.cast.service.CastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CastController}
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class CastControllerTest {
    private final static long CAST_ID = 1L;

    @InjectMocks
    private CastController castController;

    @Mock
    private CastService castService;

    @Test
    void getCastMoviesTest() {
        final var pageable = Pageable.unpaged();
        when(castService.getCastMovies(CAST_ID, pageable)).thenReturn("Mocked body");

        final var result = castController.getCastMovies(CAST_ID, pageable);
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("Mocked body");
    }
}
