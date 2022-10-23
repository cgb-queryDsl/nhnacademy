package com.nhnacademy.edu.springframework.project.report;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;

import java.util.Collection;

public interface ResultReport {
    Collection<WaterBill> resultPrint(Collection<WaterBill> data);
}
