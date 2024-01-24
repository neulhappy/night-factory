package org.recorder.nightfactory.domain;

import java.util.HashMap;
import java.util.Map;


public enum Difficulty {
    EASY(1),
    NORMAL(2),
    HARD(3),
    EXPERT(4),
    MASTER(5);

    private final int ordinal;
    private static final Map<Integer, Difficulty> difficultyNameMap = new HashMap<>();

    static {
        for (Difficulty difficulty : values()) {
            difficultyNameMap.put(difficulty.getOrdinal(), difficulty);
        }
    }

    Difficulty(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getName() {
        return name();
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static Difficulty getFromInt(int ordinal) {
        return difficultyNameMap.get(ordinal);
    }
}