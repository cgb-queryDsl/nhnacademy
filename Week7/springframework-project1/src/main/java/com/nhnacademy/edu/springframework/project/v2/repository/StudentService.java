package com.nhnacademy.edu.springframework.project.v2.repository;

import com.nhnacademy.edu.springframework.project.v2.service.Student;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Collection;

@EnableAspectJAutoProxy
public interface StudentService {

    Collection<Student> getPassedStudents();

    Collection<Student> getStudentsOrderByScore();
}
