package com.nhnacademy.gw1.parking.repository;

import java.util.Arrays;
import java.util.List;

public class Entrance {

    private List<String> entranceList;

    public Entrance() {
        this.entranceList = Arrays.asList(
                "Gate-1",
                "Gate-2"
        );
    }

    public String getEntrance(String enterGate) {
        for(String s : entranceList) {
            if (s.equals(enterGate))
                return s;
        }

        return "invalid enterGate";
    }
}
