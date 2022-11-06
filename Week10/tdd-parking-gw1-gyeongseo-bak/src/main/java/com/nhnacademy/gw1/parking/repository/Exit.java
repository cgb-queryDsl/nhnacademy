package com.nhnacademy.gw1.parking.repository;

import java.util.Arrays;
import java.util.List;

public class Exit {

    private List<String> exitList;

    public Exit() {
        this.exitList = Arrays.asList(
                "Exit-1",
                "Exit-2"
        );
    }

    public String getExit(String exitGate) {
        for(String s : exitList) {
            if (s.equals(exitGate))
                return s;
        }

        return "invalid exitGate";
    }
}
