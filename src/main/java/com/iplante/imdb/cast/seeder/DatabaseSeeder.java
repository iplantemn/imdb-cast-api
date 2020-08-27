package com.iplante.imdb.cast.seeder;

import com.iplante.imdb.cast.entity.Cast;
import com.iplante.imdb.cast.service.CastService;
import com.iplante.imdb.cast.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * On app startup, seed the database if necessary.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
@Component
public class DatabaseSeeder {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DatabaseSeeder.class));

    private final CastService castService;

    @Autowired
    public DatabaseSeeder(CastService castService) {
        this.castService = castService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (castService.getCastCount() == 0) {
            LOGGER.info("Seeding database");
            populateCastTable(2000);
            LOGGER.info("Done seeding database");
        } else {
            LOGGER.info("Database already contains data - skipping seeding step");
        }
    }

    /**
     * Populate the {@link Cast} table with a given amount of rows.
     *
     * @param seed the number of {@link Cast} entries to generate.
     */
    private void populateCastTable(int seed) {
        final var cast = IntStream
                .range(0, seed)
                .mapToObj(this::generateCastMember)
                .collect(Collectors.toList());

        castService.addCast(cast);
    }

    /**
     * Generate a {@link Cast} object with random data.
     *
     * @param num ignored parameter.
     * @return a {@link Cast} member.
     */
    private Cast generateCastMember(int num) {
        return Cast
                .builder()
                .firstName(RandomUtil.generateRandomString(6, 12))
                .lastName(RandomUtil.generateRandomString(6, 12))
                .dateOfBirth(RandomUtil.generateRandomDate(1930, 2010))
                .bio(RandomUtil.generateRandomString(20, 100))
                .build();
    }
}
