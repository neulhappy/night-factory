package org.recorder.nightfactory.domain;
//todo:장르 열거형 채워넣기

public enum Genre {
    Mystery("Mystery"),
    Gore("Gore"),
    Occult("Occult");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 문자열로부터 Genre 열거형 상수를 반환하는 메서드
    public static Genre fromString(String text) {
        if (text != null) {
            for (Genre genre : Genre.values()) {
                if (text.equalsIgnoreCase(genre.value)) {
                    return genre;
                }
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}