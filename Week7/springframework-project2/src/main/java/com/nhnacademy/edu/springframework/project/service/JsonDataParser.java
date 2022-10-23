package com.nhnacademy.edu.springframework.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@EnableAspectJAutoProxy
//@Primary
public class JsonDataParser implements DataParser {

    private List<String> jsonData;

    @Override
    public List<String> parse(String path) {
        jsonData = new ArrayList<>();
        InputStream is = this.get(path);
        String allData = "";

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;

            while ((line = reader.readLine()) != null) {
                allData+=line+"\n";
            }
        } catch (IOException e) {
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = null;
        try {
            list = mapper.readValue(allData, new TypeReference<List<Map<String, Object>>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for(Map<String, Object> s : list) {
            String inLine = s.toString();
            inLine = inLine.replace(", ", "=");
            String temp [] = inLine.substring(1, inLine.length()-1).split("=");

            String newStr = "";
            for(String t : temp) {
                newStr += t + " ";
            }

            String temp2[] = newStr.split(" ");
            String result = temp2[1] +", " + temp2[3] +", " + temp2[5] +", "+ temp2[7] +", "+ temp2[9]+", " + temp2[11] +", "+  temp2[13] + ", ";

            jsonData.add(result);
        }

        return jsonData;
    }

    private InputStream get(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if(inputStream == null) {
            throw new IllegalArgumentException();
        } else{
            return inputStream;
        }
    }
}
