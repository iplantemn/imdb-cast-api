package com.iplante.imdb.cast.service;

import com.iplante.imdb.cast.configuration.MovieApiConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MovieClient}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class MovieClientTest {

    @InjectMocks
    private MovieClient movieClient;

    @Mock
    private MovieApiConfiguration movieApiConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getCastMovies(@Mock ResponseEntity<Object> responseEntity) {
        final var captor = ArgumentCaptor.forClass(String.class);
        final var pageable = PageRequest.of(4, 10, Sort.unsorted());

        when(responseEntity.getBody()).thenReturn("Mocked body");
        when(restTemplate.getForEntity(anyString(), eq(Object.class))).thenReturn(responseEntity);

        movieClient.getCastMovies(1L, pageable);
        verify(restTemplate).getForEntity(captor.capture(), eq(Object.class));

        final var uri = captor.getValue();
        assertThat(uri).contains("castId=1");
        assertThat(uri).contains("size=10");
        assertThat(uri).contains("page=4");
    }
}
