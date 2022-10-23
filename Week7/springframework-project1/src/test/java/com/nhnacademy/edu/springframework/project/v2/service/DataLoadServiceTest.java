package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.Main;
import com.nhnacademy.edu.springframework.project.v2.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class})
public class DataLoadServiceTest {

    @Autowired
    private Students students;
    @Autowired
    private DataLoadService dataLoadService;

    @Test
    void loadAndMerge() {
        // given

        // when
        dataLoadService.loadAndMerge();

        // then
        Assertions.assertThat(students.findAll()).hasSize(4);
    }
}
