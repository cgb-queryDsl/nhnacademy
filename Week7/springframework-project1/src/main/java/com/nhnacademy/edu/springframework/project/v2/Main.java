package com.nhnacademy.edu.springframework.project.v2;

import com.nhnacademy.edu.springframework.project.v2.repository.StudentService;
import com.nhnacademy.edu.springframework.project.v2.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Collection;

@ComponentScan
@EnableAspectJAutoProxy
@Configuration
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        DataLoadService dataLoadService = context.getBean(DataLoadService.class);
        dataLoadService.loadAndMerge();

        StudentService studentService = context.getBean(StudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);
        System.out.println("----------------------------------");

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
        System.out.println("----------------------------------");

        GradeQueryService gradeQueryService = context.getBean(GradeQueryService.class);
        System.out.println(gradeQueryService.getScoreByStudentName("A"));
        System.out.println("----------------------------------");

        System.out.println(gradeQueryService.getScoreByStudentSeq(12));
        System.out.println("----------------------------------");
    }
}
