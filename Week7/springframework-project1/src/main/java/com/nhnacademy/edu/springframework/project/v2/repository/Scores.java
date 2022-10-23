package com.nhnacademy.edu.springframework.project.v2.repository;

import java.util.List;

public interface Scores {
    void load();

    List<Score> findAll();
}
