//package com.nhnacademy.edu.springboot.student.domain;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Transactional
//@SpringBootTest
//class StudentRepositoryTest {
//
//    @Autowired
//    StudentRepository studentRepository;
//
//    @Test
//    void testStudentRepository() throws Exception {
//        // given
//        Student gs = new Student(1L, "gs", 80);
//        studentRepository.save(gs);
//
//        // when
//        Optional<Student> actual = studentRepository.findById(1L);
//
//        // then
//        assertThat(actual).isPresent();
//        assertThat(actual.get()).isEqualTo(gs);
//    }
//
//}