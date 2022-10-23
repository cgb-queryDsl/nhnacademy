package com.nhnacademy.edu.springframework.project.v1;

import com.nhnacademy.edu.springframework.project.v1.service.*;
import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;

import java.util.Collection;

public class Main {

    // TODO 9! - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        GradeQueryService gradeQueryService = new DefaultGradeQueryService();
        System.out.println(gradeQueryService.getScoreByStudentName("A"));
        System.out.println(gradeQueryService.getScoreByStudentSeq(12));
    }
}
