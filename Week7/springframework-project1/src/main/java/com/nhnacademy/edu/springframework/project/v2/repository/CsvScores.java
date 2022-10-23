package com.nhnacademy.edu.springframework.project.v2.repository;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvScores implements Scores {

    private List<Score> scoresRepository;

    @Override
    public void load() {
        scoresRepository = new ArrayList<>();
        URL resource = CsvScores.class.getResource("/data/score.csv");

        if (resource == null) {
            throw new NullPointerException("파일이 없음");
        }

        try{
            List<String> data = Files.readAllLines(Paths.get(resource.toURI()));

            for(String s : data) {
                String temp [] = s.split(",");
                scoresRepository.add(new Score(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> findAll() {
        return scoresRepository;
    }
}
