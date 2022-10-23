package com.nhnacademy.edu.springframework.project.v1.repository;

import com.nhnacademy.edu.springframework.project.v1.service.Student;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvStudents implements Students {

    private static final CsvStudents instance = new CsvStudents();

    private static Collection<Student> studentsRepository;

    private CsvStudents() {
        this.studentsRepository = new ArrayList<>();
    }

    /** TODO 3! :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        return instance;
    }

    // TODO 7! : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        URL resource = CsvStudents.class.getResource("/data/student.csv");

        if(resource == null) {
            throw new NullPointerException("파일이 없음");
        }

        try {
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

//        studentsRepository.forEach(System.out::print);
    }

    @Override
    public Collection<Student> findAll() {
        return studentsRepository;
    }

    /**
     * TODO 8! : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for(Student student : studentsRepository) {
            for(Score score : scores) {
                if (student.getSeq() == score.getStudentSeq()) {
                    student.setScore(score);
                    break;
                }
            }
        }

//        studentsRepository.forEach(System.out::print);
    }
}
