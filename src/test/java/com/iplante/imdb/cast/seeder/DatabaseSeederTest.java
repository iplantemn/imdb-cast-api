package com.iplante.imdb.cast.seeder;

import com.iplante.imdb.cast.service.CastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link DatabaseSeeder}
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class DatabaseSeederTest {

    @InjectMocks
    private DatabaseSeeder databaseSeeder;

    @Mock
    private CastService castService;

    @Test
    void seedWhenCastCountIsMoreThanZero() {
        when(castService.getCastCount()).thenReturn(100L);
        databaseSeeder.seed(null);
        verify(castService, never()).addCast(anyList());
    }

    @Test
    void seedWhenCastCountIsZero() {
        final var captor = ArgumentCaptor.forClass(List.class);

        when(castService.getCastCount()).thenReturn(0L);
        databaseSeeder.seed(null);
        verify(castService).addCast(captor.capture());
        assertThat(captor.getValue()).hasSize(2000);
    }
}
