package es.thefactory.hellospringdatajpa.biz.mapper;

import es.thefactory.hellospringdatajpa.biz.domain.Player;
import es.thefactory.hellospringdatajpa.dal.entity.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Pablo Lorenzo Manzano.
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper {

    /**
     *
     * @param playerEntity
     * @return Player
     */
    Player toDomain(PlayerEntity playerEntity);

    /**
     *
     * @param playerEntityList
     * @return List<Player>
     */
    List<Player> toDomain(List<PlayerEntity> playerEntityList);

    /**
     *
     * @param player
     * @return PlayerEntity
     */
    PlayerEntity toEntity(Player player);

    /**
     *
     * @param player
     * @param playerEntity
     */
    void updateEntity(Player player, @MappingTarget PlayerEntity playerEntity);
}
