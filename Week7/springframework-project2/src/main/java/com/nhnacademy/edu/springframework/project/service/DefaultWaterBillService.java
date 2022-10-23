package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@EnableAspectJAutoProxy
public class DefaultWaterBillService implements WaterBillService {

    private BillRepository billRepository;
    private Collection<WaterBill> list;

    @Autowired
    public DefaultWaterBillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Collection<WaterBill> calculate(long usage) {
        list = new ArrayList<>();
        list = billRepository.setRepository(usage);

        for(WaterBill waterBill : list) {
            if(waterBill.getBillTotal() != Long.MAX_VALUE) {
                long unitPrice = waterBill.getUnitPrice();
                waterBill.setBillTotal(unitPrice * usage);
            }
        }

        return list.stream().sorted(Comparator.comparing((WaterBill w) -> w.getBillTotal()))
                .collect(Collectors.toList());
    }
}
