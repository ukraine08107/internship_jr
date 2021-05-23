package com.game.service.impl;

import com.game.controller.PlayerOrder;
import com.game.entity.PLayerSearchFilter;
import com.game.entity.Player;
import com.game.model.CreatePlayerRequest;
import com.game.model.GetPlayersCountRequest;
import com.game.model.GetPlayersListRequest;
import com.game.model.PageFilter;
import com.game.model.PlayerModel;
import com.game.model.UpdatePlayerRequest;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import com.game.utils.Mapper;
import com.game.utils.Updater;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultPlayerService implements PlayerService {
    private final PlayerRepository playerRepository;

    public DefaultPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerModel> getPlayersList(GetPlayersListRequest request) {
        PageFilter pageFilter = Mapper.getPlayersListRequestToPageFilter(request);
        PLayerSearchFilter pLayerSearchFilter = Mapper.getPlayersListRequestToPLayerSearchFilter(request);
        List<Player> players = playerRepository.getAllByParams(pLayerSearchFilter);
        return filterByPage(players, pageFilter).stream()
                .map(Mapper::playerToPlayerModel)
                .collect(Collectors.toList());
    }

    private List<Player> filterByPage(List<Player> players, PageFilter pageFilter) {
        if (players == null || players.isEmpty()) {
            return Collections.emptyList();
        }
        int firstIndex = pageFilter.getPageNumber() * pageFilter.getPageSize();
        int lastIndex = firstIndex + pageFilter.getPageSize();
        if (players.size() <= firstIndex) {
            return Collections.emptyList();
        }

        players.sort((o1, o2) -> {
            PlayerOrder playerOrder = pageFilter.getOrder();
            if (playerOrder == PlayerOrder.ID) {
                return o1.getId().compareTo(o2.getId());
            } else if (playerOrder == PlayerOrder.NAME) {
                return o1.getName().compareTo(o2.getName());
            } else if (playerOrder == PlayerOrder.EXPERIENCE) {
                return o1.getExperience().compareTo(o2.getExperience());
            } else if (playerOrder == PlayerOrder.BIRTHDAY) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            } else if (playerOrder == PlayerOrder.LEVEL) {
                return o1.getLevel().compareTo(o2.getLevel());
            } else {
                return 0;
            }
        });
        return players.subList(firstIndex, Math.min(lastIndex, players.size()));
    }

    @Override
    public Integer getPlayersCount(GetPlayersCountRequest request) {
        PLayerSearchFilter pLayerSearchFilter = Mapper.getPlayersCountRequestToPLayerSearchFilter(request);
        return playerRepository.getPlayersCountByParams(pLayerSearchFilter);
    }

    @Transactional
    @Override
    public PlayerModel createPlayer(CreatePlayerRequest request) {
        Player player = Mapper.createPlayerRequestToPlayer(request);
        Player savedPlayer = playerRepository.insertPlayer(player);
        return Mapper.playerToPlayerModel(savedPlayer);
    }

    @Override
    public PlayerModel getPlayer(Long playerId) {
        Player founded = playerRepository.getByIdWithException(playerId);
        return Mapper.playerToPlayerModel(founded);
    }

    @Transactional
    @Override
    public PlayerModel updatePlayer(Long playerId, UpdatePlayerRequest request) {
        Player target = playerRepository.getByIdWithException(playerId);
        Player source = Mapper.updatePlayerRequestToPlayer(request);
        Updater.updatePlayer(target, source);
        Player updated = playerRepository.updatePlayer(target);
        return Mapper.playerToPlayerModel(updated);
    }

    @Transactional
    @Override
    public void deletePlayer(Long playerId) {
        Player target = playerRepository.getByIdWithException(playerId);
        playerRepository.deletePlayer(target.getId());
    }
}
