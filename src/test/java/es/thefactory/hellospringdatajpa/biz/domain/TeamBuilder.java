package es.thefactory.hellospringdatajpa.biz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.UUID;

/**
 * @author Pablo Lorenzo Manzano.
 */
@With
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TeamBuilder {

    /**
     *
     */
    private UUID teamId = null;

    /**
     *
     */
    private String name = "Equipo 1";

    /**
     *
     * @return TeamBuilder
     */
    public static TeamBuilder aTeam() {
        return (new TeamBuilder());
    }

    /**
     *
     * @return Team
     */
    public Team build() {
        return (new Team(teamId, name));
    }
}
