package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentsRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody StudentsRequest studentsRequest) {
        Student student = studentRepository.register(studentsRequest.getName(),
                                                    studentsRequest.getEmail(),
                                                    studentsRequest.getScore(),
                                                    studentsRequest.getComment());

        return student;
    }

    @GetMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable("studentId") Long studentId ) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        return studentRepository.getStudent(studentId);
    }

    @PutMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student putStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentsRequest studentsRequest) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        Student student = studentRepository.getStudent(studentId);

        student.setName(studentsRequest.getName());
        student.setEmail(studentsRequest.getEmail());
        student.setScore(studentsRequest.getScore());
        student.setComment(studentsRequest.getComment());
        return student;
    }
}
