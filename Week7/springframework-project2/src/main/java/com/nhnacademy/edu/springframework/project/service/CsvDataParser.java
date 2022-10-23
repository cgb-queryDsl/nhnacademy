package com.nhnacademy.edu.springframework.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableAspectJAutoProxy
@Primary
public class CsvDataParser implements DataParser {

    private List<String> csvData;

    @Override
    public List<String> parse(String path) {
        URL resource = CsvDataParser.class.getResource(path);
        csvData = new ArrayList<>();

        try {
            csvData = Files.readAllLines(Paths.get(resource.toURI()));
        } catch (IOException e) {
            System.out.println("파일 parse 실패");
        } catch (URISyntaxException e) {
            System.out.println("파일 parse 실패");
        }

        return csvData;
    }

}
