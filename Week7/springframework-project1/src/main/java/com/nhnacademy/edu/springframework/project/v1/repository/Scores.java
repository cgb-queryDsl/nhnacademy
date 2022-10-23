package com.nhnacademy.edu.springframework.project.v1.repository;

import java.util.List;

public interface Scores {
    void load();

    List<Score> findAll();
}
