package com.iplante.imdb.cast.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

/**
 * Random data generator.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
public class RandomUtil {

    private RandomUtil() {
        // no instantiation
    }

    /**
     * Generate a random Date object between a two bounding years.
     *
     * @param minYear the minimum year for the date.
     * @param maxYear the maximum year for the date.
     * @return a random Date between the boundaries.
     */
    public static Date generateRandomDate(int minYear, int maxYear) {
        final var minEpoch = (int) LocalDate.of(minYear, 1, 1).toEpochDay();
        final var maxEpoch = (int) LocalDate.of(maxYear, 1, 1).toEpochDay();
        final var randomEpoch = minEpoch + new Random().nextInt(maxEpoch - minEpoch);

        return new Date(randomEpoch);
    }

    /**
     * Generate a random integer between two given numbers.
     *
     * @param min the minimum number that can be returned.
     * @param max the maximum number that can be returned.
     * @return a bounded random integer.
     */
    public static int generateRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * Generate a random String of a bounded random length.
     *
     * @param minLength the minimum length that the random string can be.
     * @param maxLength the maximum length that the string can be.
     * @return the random String of bounded random length.
     */
    public static String generateRandomString(int minLength, int maxLength) {
        return RandomStringUtils.randomAlphabetic(generateRandomInt(minLength, maxLength));
    }

}
