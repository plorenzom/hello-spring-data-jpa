package es.thefactory.hellospringdatajpa.biz.mapper;

import es.thefactory.hellospringdatajpa.biz.domain.Team;
import es.thefactory.hellospringdatajpa.dal.entity.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Pablo Lorenzo Manzano.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {

    /**
     *
     * @param teamEntity
     * @return Team
     */
    Team toDomain(TeamEntity teamEntity);

    /**
     *
     * @param teamEntityList
     * @return List<Team>
     */
    List<Team> toDomain(List<TeamEntity> teamEntityList);

    /**
     *
     * @param team
     * @return TeamEntity
     */
    TeamEntity toEntity(Team team);

    /**
     *
     * @param team
     * @param teamEntity
     */
    void updateEntity(Team team, @MappingTarget TeamEntity teamEntity);
}
