package es.thefactory.hellospringdatajpa.dal.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

/**
 * @author Pablo Lorenzo Manzano.
 */
@With
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TeamEntityBuilder {

    /**
     *
     */
    private String name = "Equipo 1";

    /**
     *
     * @return TeamEntityBuilder
     */
    public static TeamEntityBuilder aTeam() {
        return (new TeamEntityBuilder());
    }

    /**
     *
     * @return TeamEntity
     */
    public TeamEntity build() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(name);

        return teamEntity;
    }
}
