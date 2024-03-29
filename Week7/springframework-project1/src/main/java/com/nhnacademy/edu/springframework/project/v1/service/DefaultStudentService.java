package com.nhnacademy.edu.springframework.project.v1.service;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v1.repository.StudentService;
import com.nhnacademy.edu.springframework.project.v1.repository.Students;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    @Override
    public Collection<Student> getPassedStudents() {
        // TODO 1! : pass한 학생만 반환하도록 수정하세요.
        // Student 는 Score 를 갖고 있고 Score 에는 pass 여부를 알수 있는 메서드가 있습니다.
        // Java stream api 의 filter() 를 사용하여 필터링된 Student 객체를 리턴 하세요. (Students 와 Student 는 다릅니다.)

        Students studentRepository = CsvStudents.getInstance();

        List<Student> passList = studentRepository.findAll().stream()
                .filter(g -> g.getScore().isFail() == false).collect(Collectors.toList());

        return passList;
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        // TODO 4! : 성적 순으로 학생 정보(Student)를 반환합니다.
        // 소팅 문제입니다. Java Stream API 의 소팅 관련 메서드를 사용하세요.

        Students studentRepository = CsvStudents.getInstance();

        List<Student> orderList = studentRepository.findAll().stream()
                .sorted(Comparator.comparing((Student s) -> s.getScore().getScore()).reversed())
                .collect(Collectors.toList());

        // student 에 들어가서 score 객체의 점수를 가지고 내림차순 정렬
        // Student(seq, name, Score(seq, 점수));
        // a = (1, "abc", (1, 100) );
        // a = (1, "abc", (1, 100) );
        // a = (1, "abc", (1, 100) );
        // a = (1, "abc", (1, 100) );
        // a = (1, "abc", (1, 100) );
        // a = (1, "abc", (1, 100) );

        return orderList;
    }

}
