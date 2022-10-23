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
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BootStrap.class})
class BillRepositoryTest {

    @Autowired
    private DataParser dataParser;
    @Autowired
    private BillRepository billRepository;

    String csvPath = "/data/Tariff_20220331.csv";

    @Test
    void load() {
        List<String> list = dataParser.parse(csvPath);
        Assertions.assertThat(list.size()).isEqualTo(100);
    }

    @Test
    void setRepository() {
        String path = "/data/Tariff_20220331.csv";
        billRepository.load(csvPath);

        Collection<WaterBill> billList = billRepository.setRepository(1000);

        boolean check = true;

        for(WaterBill waterBill : billList) {
            if (waterBill.getBillTotal() == 1 || waterBill.getBillTotal() == Long.MAX_VALUE) {
                check = true;
            } else{
                check = false;
            }
        }

        Assertions.assertThat(check).isEqualTo(true);
    }
}