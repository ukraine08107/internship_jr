package com.game.utils;

import com.game.controller.PlayerOrder;
import com.game.entity.PLayerSearchFilter;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.model.CreatePlayerRequest;
import com.game.model.GetPlayersCountRequest;
import com.game.model.GetPlayersListRequest;
import com.game.model.PageFilter;
import com.game.model.PlayerModel;
import com.game.model.UpdatePlayerRequest;

import java.sql.Date;
import java.util.Map;
import java.util.stream.Stream;

public final class Mapper {
    private static final String URL_PARAM_NAME = "name";
    private static final String URL_PARAM_TITLE = "title";
    private static final String URL_PARAM_RACE = "race";
    private static final String URL_PARAM_PROFESSION = "profession";
    private static final String URL_PARAM_AFTER = "after";
    private static final String URL_PARAM_BEFORE = "before";
    private static final String URL_PARAM_BANNED = "banned";
    private static final String URL_PARAM_MIN_EXPERIENCE = "minExperience";
    private static final String URL_PARAM_MAX_EXPERIENCE = "maxExperience";
    private static final String URL_PARAM_MIN_LEVEL = "minLevel";
    private static final String URL_PARAM_MAX_LEVEL = "maxLevel";
    private static final String URL_PARAM_ORDER = "order";
    private static final String URL_PARAM_PAGE_NUMBER = "pageNumber";
    private static final String URL_PARAM_PAGE_SIZE = "pageSize";

    private Mapper() {
        throw new UnsupportedOperationException("Constructor invoke not allowed");
    }

    public static GetPlayersListRequest urlParamsToGetPlayersListRequest(Map<String, String> urlParams) {
        GetPlayersListRequest request = new GetPlayersListRequest();
        if (urlParams != null && !urlParams.isEmpty()) {
            request.setName(urlParams.get(URL_PARAM_NAME));
            request.setTitle(urlParams.get(URL_PARAM_TITLE));
            request.setRace(stringToRace(urlParams.get(URL_PARAM_RACE)));
            request.setProfession(stringToProfession(urlParams.get(URL_PARAM_PROFESSION)));
            request.setAfter(stringToLong(urlParams.get(URL_PARAM_AFTER)));
            request.setBefore(stringToLong(urlParams.get(URL_PARAM_BEFORE)));
            request.setBanned(stringToBoolean(urlParams.get(URL_PARAM_BANNED)));
            request.setMinExperience(stringToInteger(urlParams.get(URL_PARAM_MIN_EXPERIENCE)));
            request.setMaxExperience(stringToInteger(urlParams.get(URL_PARAM_MAX_EXPERIENCE)));
            request.setMinLevel(stringToInteger(urlParams.get(URL_PARAM_MIN_LEVEL)));
            request.setMaxLevel(stringToInteger(urlParams.get(URL_PARAM_MAX_LEVEL)));
            request.setOrder(stringToPlayerOrder(urlParams.get(URL_PARAM_ORDER)));
            request.setPageNumber(stringToInteger(urlParams.get(URL_PARAM_PAGE_NUMBER)));
            request.setPageSize(stringToInteger(urlParams.get(URL_PARAM_PAGE_SIZE)));
        }
        return request;
    }

    public static GetPlayersCountRequest urlParamsToGetPlayersCountRequest(Map<String, String> urlParams) {
        GetPlayersCountRequest request = new GetPlayersCountRequest();
        if (urlParams != null && !urlParams.isEmpty()) {
            request.setName(urlParams.get(URL_PARAM_NAME));
            request.setTitle(urlParams.get(URL_PARAM_TITLE));
            request.setRace(stringToRace(urlParams.get(URL_PARAM_RACE)));
            request.setProfession(stringToProfession(urlParams.get(URL_PARAM_PROFESSION)));
            request.setAfter(stringToLong(urlParams.get(URL_PARAM_AFTER)));
            request.setBefore(stringToLong(urlParams.get(URL_PARAM_BEFORE)));
            request.setBanned(stringToBoolean(urlParams.get(URL_PARAM_BANNED)));
            request.setMinExperience(stringToInteger(urlParams.get(URL_PARAM_MIN_EXPERIENCE)));
            request.setMaxExperience(stringToInteger(urlParams.get(URL_PARAM_MAX_EXPERIENCE)));
            request.setMinLevel(stringToInteger(urlParams.get(URL_PARAM_MIN_LEVEL)));
            request.setMaxLevel(stringToInteger(urlParams.get(URL_PARAM_MAX_LEVEL)));
        }
        return request;
    }

    public static Player createPlayerRequestToPlayer(CreatePlayerRequest request) {
        if (request == null) {
            return null;
        }

        Player player = new Player();
        player.setName(request.getName());
        player.setTitle(request.getTitle());
        player.setRace(request.getRace());
        player.setProfession(request.getProfession());
        player.setExperience(request.getExperience());
        player.setLevel(LevelUtils.calculateCurrentPlayerLevel(player.getExperience()));
        player.setUntilNextLevel(LevelUtils.calculateRemainingExperience(player.getLevel(), player.getExperience()));
        player.setBirthday(longToDate(request.getBirthday()));
        player.setBanned(booleanToBoolean(request.getBanned()));
        return player;
    }

    public static Player updatePlayerRequestToPlayer(UpdatePlayerRequest request) {
        if (request == null) {
            return null;
        }

        Player player = new Player();
        player.setName(request.getName());
        player.setTitle(request.getTitle());
        player.setRace(request.getRace());
        player.setProfession(request.getProfession());
        player.setExperience(request.getExperience());
        if (player.getExperience() != null) {
            player.setLevel(LevelUtils.calculateCurrentPlayerLevel(player.getExperience()));
            player.setUntilNextLevel(LevelUtils.calculateRemainingExperience(player.getLevel(), player.getExperience()));
        }
        player.setBirthday(longToDate(request.getBirthday()));
        player.setBanned(booleanToBoolean(request.getBanned()));
        return player;
    }

    public static PlayerModel playerToPlayerModel(Player player) {
        if (player == null) {
            return null;
        }

        PlayerModel playerModel = new PlayerModel();
        playerModel.setId(player.getId());
        playerModel.setName(player.getName());
        playerModel.setTitle(player.getTitle());
        playerModel.setRace(player.getRace());
        playerModel.setProfession(player.getProfession());
        playerModel.setBirthday(dateToLong(player.getBirthday()));
        playerModel.setBanned(player.getBanned());
        playerModel.setExperience(player.getExperience());
        playerModel.setLevel(player.getLevel());
        playerModel.setUntilNextLevel(player.getUntilNextLevel());
        return playerModel;
    }

    public static PLayerSearchFilter getPlayersListRequestToPLayerSearchFilter(GetPlayersListRequest request) {
        PLayerSearchFilter filter = new PLayerSearchFilter();
        if (request != null) {
            filter.setName(request.getName());
            filter.setTitle(request.getTitle());
            filter.setRace(request.getRace());
            filter.setProfession(request.getProfession());
            filter.setAfter(longToDate(request.getAfter()));
            filter.setBefore(longToDate(request.getBefore()));
            filter.setBanned(request.getBanned());
            filter.setMinExperience(request.getMinExperience());
            filter.setMaxExperience(request.getMaxExperience());
            filter.setMinLevel(request.getMinLevel());
            filter.setMaxLevel(request.getMaxLevel());
        }
        return filter;
    }

    public static PLayerSearchFilter getPlayersCountRequestToPLayerSearchFilter(GetPlayersCountRequest request) {
        PLayerSearchFilter filter = new PLayerSearchFilter();
        if (request != null) {
            filter.setName(request.getName());
            filter.setTitle(request.getTitle());
            filter.setRace(request.getRace());
            filter.setProfession(request.getProfession());
            filter.setAfter(longToDate(request.getAfter()));
            filter.setBefore(longToDate(request.getBefore()));
            filter.setBanned(request.getBanned());
            filter.setMinExperience(request.getMinExperience());
            filter.setMaxExperience(request.getMaxExperience());
            filter.setMinLevel(request.getMinLevel());
            filter.setMaxLevel(request.getMaxLevel());
        }
        return filter;
    }

    public static PageFilter getPlayersListRequestToPageFilter(GetPlayersListRequest request) {
        PageFilter filter = new PageFilter();
        if (request == null) {
            filter.setOrder(PlayerOrder.ID);
            filter.setPageNumber(0);
            filter.setPageSize(3);
        } else {
            filter.setOrder(request.getOrder() == null ? PlayerOrder.ID : request.getOrder());
            filter.setPageNumber(request.getPageNumber() == null ? 0 : request.getPageNumber());
            filter.setPageSize(request.getPageSize() == null ? 3 : request.getPageSize());
        }
        return filter;
    }

    public static Race stringToRace(String s) {
        return s == null ? null :
                Stream.of(Race.values())
                        .filter(r -> r.name().equals(s))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown Race value: " + s));
    }

    public static Profession stringToProfession(String s) {
        return s == null ? null :
                Stream.of(Profession.values())
                        .filter(r -> r.name().equals(s))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown Profession value: " + s));
    }

    public static PlayerOrder stringToPlayerOrder(String s) {
        return s == null ? null :
                Stream.of(PlayerOrder.values())
                        .filter(r -> r.name().equals(s))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown PlayerOrder value: " + s));
    }

    public static Long stringToLong(String s) {
        try {
            return s == null ? null : Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not Long: " + s);
        }
    }

    public static Integer stringToInteger(String s) {
        try {
            return s == null ? null : Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not Integer: " + s);
        }
    }

    public static Boolean stringToBoolean(String s) {
        try {
            return s == null ? null : Boolean.parseBoolean(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not Boolean: " + s);
        }
    }

    public static Date longToDate(Long l) {
        return l == null ? null : new Date(l);
    }

    public static Long dateToLong(Date d) {
        return d == null ? null : d.getTime();
    }

    public static Boolean booleanToBoolean(Boolean b) {
        return b == null ? Boolean.FALSE : b;
    }

    public static String dateToString(Date d) {
        return d == null ? null : d.toString();
    }
}
