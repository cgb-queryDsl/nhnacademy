package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.BootStrap;
import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BootStrap.class})
class WaterBillServiceTest {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private WaterBillService waterBillService;

    String csvPath = "/data/Tariff_20220331.csv";

    @Test
    void calculate() {
        long usage = 1000;
        billRepository.load(csvPath);
        billRepository.setRepository(1000);
        Collection<WaterBill> bills = waterBillService.calculate(usage);

        boolean check = true;
        long temp = Long.MAX_VALUE;

        for(WaterBill w : bills) {
            if (w.getBillTotal() <= temp) {
                check = true;
            } else {
                check = false;
            }
            temp = w.getBillTotal();
        }

        Assertions.assertThat(check).isEqualTo(true);
    }

}