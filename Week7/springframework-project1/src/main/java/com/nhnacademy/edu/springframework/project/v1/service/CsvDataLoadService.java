package com.nhnacademy.edu.springframework.project.v1.service;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v1.repository.Scores;
import com.nhnacademy.edu.springframework.project.v1.repository.Students;

public class CsvDataLoadService implements DataLoadService {
    @Override
    public void loadAndMerge() {
        Scores scores = CsvScores.getInstance();
        scores.load();

        Students students = CsvStudents.getInstance();
        students.load();
        students.merge(scores.findAll());
    }
}
