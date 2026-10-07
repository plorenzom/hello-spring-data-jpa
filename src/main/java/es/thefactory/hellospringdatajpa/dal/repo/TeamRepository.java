package es.thefactory.hellospringdatajpa.dal.repo;

import es.thefactory.hellospringdatajpa.dal.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Pablo Lorenzo Manzano.
 */
@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {
}
