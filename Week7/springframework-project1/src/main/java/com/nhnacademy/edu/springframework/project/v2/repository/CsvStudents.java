package com.nhnacademy.edu.springframework.project.v2.repository;

import com.nhnacademy.edu.springframework.project.v2.service.Student;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CsvStudents implements Students{


    private Collection<Student> studentsRepository;

    @Override
    public void load() {
        studentsRepository = new ArrayList<>();
        URL resource = CsvStudents.class.getResource("/data/student.csv");

        if(resource == null) {
            throw new NullPointerException("파일이 없음");
        }

        try{
            List<String> data = Files.readAllLines(Paths.get(resource.toURI()));

            for(String s : data) {
                String temp [] = s.split(",");

                studentsRepository.add(new Student(Integer.parseInt(temp[0]), temp[1]));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Student> findAll() {
        return studentsRepository;
    }

    @Override
    public void merge(Collection<Score> scores) {
        for(Student student : studentsRepository) {
            for(Score score : scores) {
                if(student.getSeq() == score.getStudentSeq()) {
                    student.setScore(score);
                    break;
                }
            }
        }
    }
}
