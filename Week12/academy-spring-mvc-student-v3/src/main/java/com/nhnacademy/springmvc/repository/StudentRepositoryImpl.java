package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StudentRepositoryImpl implements StudentRepository {

    private final Map<Long, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        long id = studentMap.keySet()
                            .stream()
                            .max(Comparator.comparing(Function.identity()))
                            .map(l -> l + 1)
                            .orElse(1L);

        Student student = new Student(id, name, email, score, comment);

        studentMap.put(id, student);

        return student;
    }

    @Override
    public Student getStudent(long id) {
        return exists(id) ? studentMap.get(id) : null;
    }

}
