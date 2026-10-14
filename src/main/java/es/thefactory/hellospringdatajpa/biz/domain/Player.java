package es.thefactory.hellospringdatajpa.biz.domain;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Pablo Lorenzo Manzano.
 */
@Builder
public record Player(

    /**
     *
     */
    UUID playerId,

    /**
     *
     */
    String name,

    /**
     *
     */
    String paternalSurname,

    /**
     *
     */
    String maternalSurname,

    /**
     *
     */
    String nickname,

    /**
     *
     */
    LocalDate birthDate,

    /**
     *
     */
    String sex) {
}
