package com.nhnacademy.edu.springframework.project.report;

import com.nhnacademy.edu.springframework.project.BootStrap;
import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.service.BillRepository;
import com.nhnacademy.edu.springframework.project.service.WaterBillService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BootStrap.class})
class ResultReportTest {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private WaterBillService waterBillService;
    @Autowired
    private ResultReport report;

    String csvPath = "/data/Tariff_20220331.csv";

    @Test
    void resultPrint() {
        long usage = 1000;
        billRepository.load(csvPath);
        billRepository.setRepository(usage);

        Collection<WaterBill> result = report.resultPrint(waterBillService.calculate(usage));

        Assertions.assertThat(result.size()).isEqualTo(5);
    }

}