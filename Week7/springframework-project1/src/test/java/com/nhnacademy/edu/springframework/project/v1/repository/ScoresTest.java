package com.nhnacademy.edu.springframework.project.v1.repository;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.v1.repository.Scores;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoresTest {

    private static final Scores scores = CsvScores.getInstance();

    @Test
    @DisplayName("score.csv load() 성공 Test")
    void load() {
        // given

        // when
        scores.load();

        // then
        Assertions.assertThat(scores.findAll()).hasSize(3);

        scores.findAll().clear();
    }

    @Test
    @DisplayName("score.csv findAll() 성공 Test")
    void findAll() {
        // given

        // when
//        scores.load();

        // then
        Assertions.assertThat(scores.findAll()).hasSize(3);

        scores.findAll().clear();
    }

    @AfterEach
    void clear() {
        scores.findAll().clear();
    }
}

