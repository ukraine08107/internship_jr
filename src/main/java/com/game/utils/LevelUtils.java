package com.game.utils;

public final class LevelUtils {
    private LevelUtils() {
        throw new UnsupportedOperationException("Constructor invoke not allowed");
    }

    public static Integer calculateCurrentPlayerLevel(Integer experience) {
        experience = experience == null ? 0 : experience;
        return (int) ((Math.sqrt(2_500 + (double) 200 * experience) - 50) / 100);
    }

    public static Integer calculateRemainingExperience(Integer currentLevel, Integer experience) {
        currentLevel = currentLevel == null ? 0 : currentLevel;
        experience = experience == null ? 0 : experience;
        return 50 * (currentLevel + 1) * (currentLevel + 2) - experience;
    }
}
