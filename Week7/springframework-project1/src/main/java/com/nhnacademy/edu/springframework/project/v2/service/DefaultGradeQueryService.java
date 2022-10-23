package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    private Students students;

    @Autowired
    public DefaultGradeQueryService(Students students) {
        this.students = students;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        List<Score> result = students.findAll().stream()
                .filter(x -> x.getName().equals(name))
                .map(Student::getScore)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        List<Score> result = students.findAll().stream()
                .filter(x -> x.getSeq() == seq)
                .map(Student::getScore)
                .collect(Collectors.toList());

        return result.get(0);
    }
}
