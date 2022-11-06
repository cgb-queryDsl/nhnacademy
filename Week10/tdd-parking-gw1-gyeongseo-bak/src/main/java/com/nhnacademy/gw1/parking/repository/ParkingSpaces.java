package com.nhnacademy.gw1.parking.repository;

import java.util.ArrayList;
import java.util.List;

import static com.nhnacademy.gw1.parking.domain.ParkingSpaceAreaCode.*;

public class ParkingSpaces {

    private final List<String> parkList = new ArrayList<>();
    private static final int SPACE_SIZE = 2;

    public ParkingSpaces() {
        for(int i = 1; i <= SPACE_SIZE; i++) {
            parkList.add(A + "-" + i);
            parkList.add(B + "-" + i);
        }
    }

    public String getParkSpace(String parkSpace) {
        for (String s : parkList) {
            if (s.equals(parkSpace)) {
                return s;
            }
        }
        return "invalid parkSpace";
    }

}
