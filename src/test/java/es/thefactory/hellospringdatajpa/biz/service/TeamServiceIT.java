package es.thefactory.hellospringdatajpa.biz.service;

import es.thefactory.hellospringdatajpa.biz.domain.Team;
import es.thefactory.hellospringdatajpa.biz.domain.TeamBuilder;
import es.thefactory.hellospringdatajpa.biz.exception.TeamNotFoundException;
import es.thefactory.hellospringdatajpa.config.AppConfig;
import es.thefactory.hellospringdatajpa.dal.entity.TeamEntity;
import es.thefactory.hellospringdatajpa.dal.entity.TeamEntityBuilder;
import es.thefactory.hellospringdatajpa.dal.repo.TeamRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Pablo Lorenzo Manzano.
 */
@SpringJUnitConfig(classes = AppConfig.class)
class TeamServiceIT {

    /**
     *
     */
    private final TeamRepository teamRepository;

    /**
     *
     */
    private final TeamService teamService;

    /**
     *
     * @param teamRepository
     * @param teamService
     */
    @Autowired
    TeamServiceIT(TeamRepository teamRepository, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    /**
     *
     */
    @BeforeEach
    void resetTestData() {
        teamRepository.deleteAllInBatch();
    }

    /**
     *
     */
    @Test
    void create_withValidTeam_savesTeam() {
        Team inputTeam = TeamBuilder.aTeam().build();

        Team actualTeam = teamService.create(inputTeam);

        assertThat(actualTeam).isNotNull();
        assertThat(actualTeam.teamId()).isNotNull();
        assertThat(actualTeam).usingRecursiveComparison().ignoringFields("teamId").isEqualTo(inputTeam);

        Optional<TeamEntity> optionalTeamEntity = teamRepository.findById(actualTeam.teamId());

        assertThat(optionalTeamEntity).isPresent();
        TeamEntity actualTeamEntity = optionalTeamEntity.get();
        assertThat(actualTeamEntity).usingRecursiveComparison().ignoringFields("teamId").isEqualTo(inputTeam);
    }

    /**
     *
     */
    @Test
    void create_withInvalidTeam_throwsConstraintViolationException() {
        Team inputTeam = TeamBuilder.aTeam().withName(null).build();

        assertThatThrownBy(() -> teamService.create(inputTeam)).isInstanceOf(ConstraintViolationException.class);

        assertThat(teamRepository.count()).isZero();
    }

    /**
     *
     */
    @Test
    void deleteById_withExistingId_deletesTeam() {
        TeamEntity actualTeamEntity = teamRepository.save(TeamEntityBuilder.aTeam().build());

        UUID teamId = actualTeamEntity.getTeamId();

        teamService.deleteById(teamId);

        assertThat(teamRepository.existsById(teamId)).isFalse();
    }

    /**
     *
     */
    @Test
    void deleteById_withNonExistingId_throwsTeamNotFoundException() {
        final UUID TEAM_ID = UUID.randomUUID();

        assertThatThrownBy(() -> teamService.deleteById(TEAM_ID)).isInstanceOf(TeamNotFoundException.class);
    }

    /**
     *
     */
    @Test
    void getAll_whenTeamsExist_returnsAllTeams() {
        List<TeamEntity> actualTeamEntityList = List.of(
            TeamEntityBuilder.aTeam().build(),
            TeamEntityBuilder.aTeam().withName("Equipo 2").build());

        actualTeamEntityList = teamRepository.saveAll(actualTeamEntityList);

        List<Team> actualTeamList = teamService.getAll();

        assertThat(actualTeamList).hasSize(actualTeamEntityList.size());
        assertThat(actualTeamList).extracting(Team::teamId).containsExactlyInAnyOrder(
            actualTeamEntityList.getFirst().getTeamId(),
            actualTeamEntityList.get(1).getTeamId());
    }

    /**
     *
     */
    @Test
    void getAll_whenNoTeamsExist_returnsEmpty() {
        List<Team> actualTeamList = teamService.getAll();

        assertThat(actualTeamList).isEmpty();
    }

    /**
     *
     */
    @Test
    void getById_withExistingId_returnsTeam() {
        TeamEntity actualTeamEntity = teamRepository.save(TeamEntityBuilder.aTeam().build());

        Optional<Team> optionalTeam = teamService.getById(actualTeamEntity.getTeamId());

        assertThat(optionalTeam).isPresent();
        Team actualTeam = optionalTeam.get();
        assertThat(actualTeam).usingRecursiveComparison().isEqualTo(actualTeamEntity);
    }

    /**
     *
     */
    @Test
    void getById_withNonExistingId_returnsEmpty() {
        final UUID TEAM_ID = UUID.randomUUID();

        Optional<Team> optionalTeam = teamService.getById(TEAM_ID);

        assertThat(optionalTeam).isEmpty();
    }

    /**
     *
     */
    @Test
    void update_whenTeamExists_updatesTeam() {
        TeamEntity actualTeamEntity = teamRepository.save(TeamEntityBuilder.aTeam().build());

        UUID teamId = actualTeamEntity.getTeamId();

        Team inputTeam = TeamBuilder.aTeam().withTeamId(teamId).withName("Equipo X").build();

        teamService.update(inputTeam);

        Optional<TeamEntity> optionalTeamEntity = teamRepository.findById(teamId);

        assertThat(optionalTeamEntity).isPresent();
        actualTeamEntity = optionalTeamEntity.get();
        assertThat(actualTeamEntity).usingRecursiveComparison().isEqualTo(inputTeam);
    }

    /**
     *
     */
    @Test
    void update_whenTeamDoesNotExist_throwsTeamNotFoundException() {
        Team inputTeam = TeamBuilder.aTeam().withTeamId(UUID.randomUUID()).withName("Equipo X").build();

        assertThatThrownBy(() -> teamService.update(inputTeam)).isInstanceOf(TeamNotFoundException.class);
    }

    /**
     *
     */
    @Test
    void update_withInvalidTeam_throwsConstraintViolationException() {
        TeamEntity actualTeamEntity = teamRepository.save(TeamEntityBuilder.aTeam().build());

        UUID teamId = actualTeamEntity.getTeamId();

        Team inputTeam = TeamBuilder.aTeam().withTeamId(teamId).withName(null).build();

        assertThatThrownBy(() -> teamService.update(inputTeam)).isInstanceOf(ConstraintViolationException.class);

        assertThat(teamRepository.findById(teamId).orElseThrow()).usingRecursiveComparison()
            .isEqualTo(actualTeamEntity);
    }
}
