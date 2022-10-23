package com.nhnacademy.edu.springframework.project.report;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@EnableAspectJAutoProxy
public class DefaultResultReport implements ResultReport {

    @Override
    public Collection<WaterBill> resultPrint(Collection<WaterBill> data) {
        return data.stream().limit(5).collect(Collectors.toList());
    }
}
