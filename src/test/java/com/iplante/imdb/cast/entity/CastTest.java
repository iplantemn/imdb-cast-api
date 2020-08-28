package com.iplante.imdb.cast.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link Cast}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
public class CastTest {

    private static final long ID = 1L;
    private static final Date DATE_OF_BIRTH = new Date();
    private static final String BIO = "Jane Doe was born in Paris, France on January 10th, 1981";
    private static final String FIRST_NAME = "Jane";
    private static final String LAST_NAME = "Doe";

    @Test
    void constructorTest() {
        final var cast = new Cast(ID, DATE_OF_BIRTH, FIRST_NAME, LAST_NAME, BIO);

        assertThat(cast).isNotNull();
        assertThat(cast.getId()).isEqualTo(ID);
        assertThat(cast.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(cast.getLastName()).isEqualTo(LAST_NAME);
        assertThat(cast.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
        assertThat(cast.getBio()).isEqualTo(BIO);
    }

    @Test
    void gettersAndSettersTest() {
        final var cast = new Cast();
        cast.setId(ID);
        cast.setFirstName(FIRST_NAME);
        cast.setLastName(LAST_NAME);
        cast.setDateOfBirth(DATE_OF_BIRTH);
        cast.setBio(BIO);

        assertThat(cast).isNotNull();
        assertThat(cast.getId()).isEqualTo(ID);
        assertThat(cast.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(cast.getLastName()).isEqualTo(LAST_NAME);
        assertThat(cast.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
        assertThat(cast.getBio()).isEqualTo(BIO);
    }

    @Test
    void builderTest() {
        final var cast = Cast
                .builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .bio(BIO)
                .build();

        assertThat(cast).isNotNull();
        assertThat(cast.getId()).isEqualTo(ID);
        assertThat(cast.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(cast.getLastName()).isEqualTo(LAST_NAME);
        assertThat(cast.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
        assertThat(cast.getBio()).isEqualTo(BIO);
    }

    @Test
    void builderToStringTest() {
        final var castBuilderString = Cast
                .builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .bio(BIO)
                .toString();

        assertThat(castBuilderString).isNotNull();
        assertThat(castBuilderString).contains("id=" + ID);
        assertThat(castBuilderString).contains("dateOfBirth=null");
        assertThat(castBuilderString).contains("firstName=" + FIRST_NAME);
        assertThat(castBuilderString).contains("lastName=" + LAST_NAME);
        assertThat(castBuilderString).contains("bio=" + BIO);

    }
}
