package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;

import java.util.Collection;

public interface BillRepository {

    void load(String path);

    Collection<WaterBill> setRepository(long usage);
}
