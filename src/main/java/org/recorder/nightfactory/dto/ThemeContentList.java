package org.recorder.nightfactory.dto;

import org.recorder.nightfactory.domain.Genre;
import org.recorder.nightfactory.domain.Theme;

import java.util.HashMap;
import java.util.Map;

import static org.recorder.nightfactory.domain.Genre.fromString;

public class ThemeContentList {
    private final Map<String, Theme> theme = new HashMap<>();

    public Map<String, Theme> getAllThemes() {
        return theme;
    }

    private void init() {
        theme.put("MANAGEMENT", new Theme(
                "관리실의 비밀 - 나폴리탄",
                "밤의 공장을 관리하는 관리실에서 미스테리한 사건이 일어난다. 이 미스테리를 풀어야 공장에서 탈출할 수 있다. 안내문을 읽었는가? 행운을 빈다.",
                0,
                "Level : Hard",
                "70분",
                null,
                Genre.Mystery
        ));
    }
    public ThemeContentList() {
        init();
    }
}