package org.recorder.nightfactory.dto;

public enum ThemeName {
    MANAGEMENT("관리실"),
    WORKPLACE("작업실"),
    MACHINEROOM("기계실"),
    DORMITORY("기숙사");

    private final String name;

    ThemeName(String name) {
        this.name = name;
    }
}
