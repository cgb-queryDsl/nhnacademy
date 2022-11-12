package com.nhnacademy.domain;

import java.util.HashMap;
import java.util.Map;

public class MapStudentRepository implements StudentRepository {

    private Map<String, Student> map = new HashMap<>();

    @Override
    public void addStudent(Student student) {
        map.put(student.getId(), student);
    }

    @Override
    public Student get(String id) {
        return map.get(id);
    }
}

/**
 * ServletContext 초기화 시점에 사용해라??
 * => 서블릿컨텍스트 리스너로 초기화하는 시점에
 * --> WebAppListener
 */