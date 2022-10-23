package com.nhnacademy.edu.springframework.project.v2.repository;

import com.nhnacademy.edu.springframework.project.v2.Main;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class})
class ScoresTest {

    @Autowired
    private Scores scores;

    @Test
    void load() {
        // given

        // when
        scores.load();
        // then
        Assertions.assertThat(scores.findAll()).hasSize(3);
    }

    @Test
    void findAll() {
        // given

        // when
        scores.load();

        // then
        Assertions.assertThat(scores.findAll()).hasSize(3);
    }

}

