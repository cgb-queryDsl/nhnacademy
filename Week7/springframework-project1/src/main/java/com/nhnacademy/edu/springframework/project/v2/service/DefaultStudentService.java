package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultStudentService implements StudentService {

    private Students students;

    @Autowired
    public DefaultStudentService(Students students) {
        this.students = students;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        List<Student> passList = students.findAll().stream()
                .filter(g -> g.getScore().isFail() == false)
                .collect(Collectors.toList());

        return passList;
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        List<Student> list = students.findAll().stream()
                .sorted(Comparator.comparing((Student s) -> s.getScore().getScore()).reversed())
                .collect(Collectors.toList());

        return list;
    }
}
