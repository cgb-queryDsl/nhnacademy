package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@EnableAspectJAutoProxy
public class DefaultBillRepository implements BillRepository {

    private DataParser dataParser;
    private List<String> list;
    private Collection<WaterBill> billList;

    @Autowired
    public DefaultBillRepository(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public void load(String path) {
        list = dataParser.parse(path);
    }

    @Override
    public Collection<WaterBill> setRepository(long usage) {
        if(list.isEmpty()) {
            throw new NullPointerException("파일을 읽지 못함");
        }

        billList = new ArrayList<>();

        for(String line : list) {
            String temp[] = line.split(",");

            String city = temp[1].trim();
            String sector = temp[2].trim();
            if(city.equals("지자체명") && sector.equals("업종"))
                continue;

            int phase = Integer.parseInt(temp[3].trim());
            int min = Integer.parseInt(temp[4].trim());
            int max = Integer.parseInt(temp[5].trim());
            int bill = Integer.parseInt(temp[6].trim());

            /**
             * 사용량에 따른 최종가격 세팅
             */
            if ((usage >= min) && (usage <= max)) {
                billList.add(new WaterBill(city, sector, bill, 1));
            } else {
                billList.add(new WaterBill(city, sector, bill, Long.MAX_VALUE));
            }
        }

        return billList;
    }
}
