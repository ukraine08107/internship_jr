package com.game.controller;

import com.game.model.CreatePlayerRequest;
import com.game.model.GetPlayersCountRequest;
import com.game.model.GetPlayersListRequest;
import com.game.model.PlayerModel;
import com.game.model.UpdatePlayerRequest;
import com.game.service.PlayerService;
import com.game.utils.Mapper;
import com.game.validation.ModelValidator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {
    private final PlayerService playerService;
    private final ModelValidator modelValidator;

    public PlayerController(PlayerService playerService, ModelValidator modelValidator) {
        this.playerService = playerService;
        this.modelValidator = modelValidator;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PlayerModel> getPlayersList(@RequestParam Map<String, String> urlParams) {
        GetPlayersListRequest request = Mapper.urlParamsToGetPlayersListRequest(urlParams);
        return playerService.getPlayersList(request);
    }

    @GetMapping(value = "/count",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Integer getPlayersCount(@RequestParam Map<String, String> urlParams) {
        GetPlayersCountRequest request = Mapper.urlParamsToGetPlayersCountRequest(urlParams);
        return playerService.getPlayersCount(request);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayerModel createPlayer(@RequestBody CreatePlayerRequest request) {
        modelValidator.validateCreatePlayerRequest(request);
        return playerService.createPlayer(request);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayerModel getPlayer(@PathVariable("id") Long playerId) {
        modelValidator.validatePlayerId(playerId);
        return playerService.getPlayer(playerId);
    }

    @PostMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayerModel updatePlayer(@PathVariable("id") Long playerId,
                                    @RequestBody UpdatePlayerRequest request) {
        modelValidator.validatePlayerId(playerId);
        modelValidator.validateUpdatePlayerRequest(request);
        return playerService.updatePlayer(playerId, request);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePlayer(@PathVariable("id") Long playerId) {
        modelValidator.validatePlayerId(playerId);
        playerService.deletePlayer(playerId);
    }
}
