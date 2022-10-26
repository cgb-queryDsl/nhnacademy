package com.nhnacademy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food onion1) {
        foods.add(onion1);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void remove(BuyList.Item item) {
        foods.stream().filter(x-> x.getName().equals(item.getName()))
                        .limit(1)
                        .collect(Collectors.toList())
                        .forEach(x->foods.remove(x));
    }

    @Override
    public String toString() {
        return "FoodStand{" +
                "foods=" + foods +
                '}';
    }
}
