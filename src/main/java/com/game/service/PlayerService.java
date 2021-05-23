package com.game.service;

import com.game.model.CreatePlayerRequest;
import com.game.model.GetPlayersCountRequest;
import com.game.model.GetPlayersListRequest;
import com.game.model.PlayerModel;
import com.game.model.UpdatePlayerRequest;

import java.util.List;

public interface PlayerService {
    List<PlayerModel> getPlayersList(GetPlayersListRequest request);

    Integer getPlayersCount(GetPlayersCountRequest request);

    PlayerModel createPlayer(CreatePlayerRequest request);

    PlayerModel getPlayer(Long playerId);

    PlayerModel updatePlayer(Long playerId, UpdatePlayerRequest request);

    void deletePlayer(Long playerId);
}
