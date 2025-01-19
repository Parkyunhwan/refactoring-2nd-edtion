package com.refactoring.chapter1;

import java.util.HashMap;
import java.util.Map;

public class Plays {
    Map<String, Play> playMap = new HashMap<>();

    public Plays(Map<String, Play> playMap) {
        this.playMap = playMap;
    }

    public Play get(String playID) {
        return playMap.get(playID);
    }
}
