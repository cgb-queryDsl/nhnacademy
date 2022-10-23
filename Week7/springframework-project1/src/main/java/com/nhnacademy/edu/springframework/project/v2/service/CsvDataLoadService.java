package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.repository.Scores;
import com.nhnacademy.edu.springframework.project.v2.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {

    private Scores scores;
    private Students students;

    @Autowired
    public CsvDataLoadService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }

    @Override
    public void loadAndMerge() {
        scores.load();
        students.load();

        students.merge(scores.findAll());
    }
}
