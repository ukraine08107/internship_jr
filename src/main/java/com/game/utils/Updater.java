package com.game.utils;

import com.game.entity.Player;

public final class Updater {
    private Updater() {
        throw new UnsupportedOperationException("Constructor invoke not allowed");
    }

    public static void updatePlayer(Player target, Player source) {
        if (target == null || source == null) {
            return;
        }

        if (source.getId() != null) {
            target.setId(source.getId());
        }
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getRace() != null) {
            target.setRace(source.getRace());
        }
        if (source.getProfession() != null) {
            target.setProfession(source.getProfession());
        }
        if (source.getExperience() != null) {
            target.setExperience(source.getExperience());
        }
        if (source.getLevel() != null) {
            target.setLevel(source.getLevel());
        }
        if (source.getUntilNextLevel() != null) {
            target.setUntilNextLevel(source.getUntilNextLevel());
        }
        if (source.getBirthday() != null) {
            target.setBirthday(source.getBirthday());
        }
        if (source.getBanned() != null) {
            target.setBanned(source.getBanned());
        }
    }
}
