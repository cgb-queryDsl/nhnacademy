package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.repository.Score;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAspectJAutoProxy
public interface GradeQueryService {
    List<Score> getScoreByStudentName(String name);
    Score getScoreByStudentSeq(int seq);
}
