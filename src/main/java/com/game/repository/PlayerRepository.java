package com.game.repository;

import com.game.entity.PLayerSearchFilter;
import com.game.entity.Player;
import com.game.entity.Player_;
import com.game.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {
    private static final Predicate[] PREDICATES_COLLECTION_TYPE = new Predicate[0];

    private final PlayerJPARepository playerJPARepository;
    @PersistenceContext
    private EntityManager entityManager;

    public PlayerRepository(PlayerJPARepository playerJPARepository) {
        this.playerJPARepository = playerJPARepository;
    }

    public List<Player> getAllByParams(PLayerSearchFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = builder.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);

        setWhereByFilter(query, builder, root, filter);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    public Integer getPlayersCountByParams(PLayerSearchFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Player> root = query.from(Player.class);

        setWhereByFilter(query, builder, root, filter);
        query.select(builder.count(root));

        Long result = entityManager.createQuery(query.select(builder.count(root))).getSingleResult();
        return result == null ? 0 : result.intValue();
    }

    private <T> void setWhereByFilter(CriteriaQuery<T> query,
                                      CriteriaBuilder builder,
                                      Root<Player> root,
                                      PLayerSearchFilter filter) {
        List<Predicate> predicates = new ArrayList<>(11);
        if (filter.getName() != null) {
            predicates.add(builder.like(root.get(Player_.name), '%' + filter.getName() + '%'));
        }
        if (filter.getTitle() != null) {
            predicates.add(builder.like(root.get(Player_.title), '%' + filter.getTitle() + '%'));
        }
        if (filter.getRace() != null) {
            predicates.add(builder.equal(root.get(Player_.race), filter.getRace()));
        }
        if (filter.getProfession() != null) {
            predicates.add(builder.equal(root.get(Player_.profession), filter.getProfession()));
        }
        if (filter.getAfter() != null) {
            predicates.add(builder.greaterThan(root.get(Player_.birthday), filter.getAfter()));
        }
        if (filter.getBefore() != null) {
            predicates.add(builder.lessThan(root.get(Player_.birthday), filter.getBefore()));
        }
        if (filter.getBanned() != null) {
            predicates.add(builder.equal(root.get(Player_.banned), filter.getBanned()));
        }
        if (filter.getMinExperience() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(Player_.experience), filter.getMinExperience()));
        }
        if (filter.getMaxExperience() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(Player_.experience), filter.getMaxExperience()));
        }
        if (filter.getMinLevel() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(Player_.level), filter.getMinLevel()));
        }
        if (filter.getMaxLevel() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(Player_.level), filter.getMaxLevel()));
        }

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(PREDICATES_COLLECTION_TYPE)));
        }
    }

    public Player insertPlayer(Player player) {
        return playerJPARepository.saveAndFlush(player);
    }

    public Player updatePlayer(Player player) {
        return playerJPARepository.saveAndFlush(player);
    }

    public void deletePlayer(Long playerId) {
        playerJPARepository.deleteById(playerId);
    }

    public Player getByIdWithException(Long playerId) {
        return playerJPARepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFound("Player by id=" + playerId + " not found"));
    }
}
