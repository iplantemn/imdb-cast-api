package com.iplante.imdb.cast.configuration;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link MovieApiConfiguration}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/28/20
 */
public class MovieApiConfigurationTest {

    private final MovieApiConfiguration movieApiConfiguration = new MovieApiConfiguration();

    @Test
    void testGetSetBaseUrl() {
        movieApiConfiguration.setBaseUrl("http://test.com");
        assertThat(movieApiConfiguration.getBaseUrl()).isEqualTo("http://test.com");
    }
}
