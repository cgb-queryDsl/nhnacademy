package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.report.ResultReport;
import com.nhnacademy.edu.springframework.project.service.BillRepository;
import com.nhnacademy.edu.springframework.project.service.WaterBillService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Collection;
import java.util.Scanner;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class BootStrap {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BootStrap.class);

        String csvPath =    "/data/Tariff_20220331.csv";
        String jsonPath =   "data/Tariff_20220331.json";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            int usage = scanner.nextInt();

            if (usage <= 0) {
                scanner.close();
                throw new RuntimeException("1이상의 수만 입력 가능합니다.");
            }

            BillRepository billRepository = context.getBean(BillRepository.class);
            billRepository.load(csvPath);

            WaterBillService waterBillService = context.getBean(WaterBillService.class);
            Collection<WaterBill> list = waterBillService.calculate(usage);

            ResultReport report = context.getBean(ResultReport.class);
            for(WaterBill w : report.resultPrint(list)) {
                System.out.println(w);
            }
        }

    }
}
