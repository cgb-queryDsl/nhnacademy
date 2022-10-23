package com.nhnacademy.edu.springframework.project.v2.service;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Service
@EnableAspectJAutoProxy
public interface DataLoadService {
    void loadAndMerge();
}
