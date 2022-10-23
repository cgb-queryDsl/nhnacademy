package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.Main;
import com.nhnacademy.edu.springframework.project.v2.repository.Score;
import com.nhnacademy.edu.springframework.project.v2.repository.StudentService;
import com.nhnacademy.edu.springframework.project.v2.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class})
class StudentServiceTest {

    @Autowired
    private Students students;
    @Autowired
    private DataLoadService dataLoadService;
    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("4번 seq : Score 객체를 추가시켜 성공하는 getPassedStudents Test")
    void getPassedStudents() {
        dataLoadService.loadAndMerge();
        Score newScore = new Score(4, 65);

        for(Student s : students.findAll()) {
            if (s.getScore() == null) {
                s.setScore(newScore);
            }
        }

        Collection<Student> passedStudents = studentService.getPassedStudents();

        Assertions.assertThat(passedStudents.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("seq 개수가 달라 Null Exception 나오는 getPassedStudentsFail Test")
    void getPassedStudentsFail() {
        dataLoadService.loadAndMerge();

        Assertions.assertThatThrownBy(() -> studentService.getPassedStudents())
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("4번 seq : Score 객체를 추가시켜 성공하는 getStudentsOrderByScore Test")
    void getStudentsOrderByScore() {
        dataLoadService.loadAndMerge();
        Score newScore = new Score(4, 99);
        boolean check = false;

        for(Student s : students.findAll()) {
            if(s.getScore() == null) {
                s.setScore(newScore);
            }
        }

        Collection<Student> list = studentService.getStudentsOrderByScore();

        int tempNum = Integer.MIN_VALUE;

        for(Student s: list) {
            if(s.getScore().getScore() > tempNum) {
                check = false;
                tempNum = s.getScore().getScore();
            } else {
                check = true;
                tempNum = s.getScore().getScore();
            }
        }

        Assertions.assertThat(check).isEqualTo(true);
    }

    @Test
    @DisplayName("seq 개수가 달라 Null Exception 나오는 getStudentsOrderByScore Test")
    void getStudentsOrderByScoreFail() {
        dataLoadService.loadAndMerge();

        Assertions.assertThatThrownBy(() -> studentService.getStudentsOrderByScore())
                .isInstanceOf(NullPointerException.class);
    }

}