package com.nhnacademy.edu.springframework.project.v1.service;

import com.nhnacademy.edu.springframework.project.v1.repository.Score;

import java.util.List;

public interface GradeQueryService {
    List<Score> getScoreByStudentName(String name);
    Score getScoreByStudentSeq(int seq);
}
