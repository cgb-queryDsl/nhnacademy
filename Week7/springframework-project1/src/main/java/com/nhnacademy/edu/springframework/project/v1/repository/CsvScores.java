package com.nhnacademy.edu.springframework.project.v1.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    private static List<Score> scoresRepository;

    /** TODO 2! :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    private static final CsvScores instance = new CsvScores();

    private CsvScores(){
        this.scoresRepository = new ArrayList<>();
    }
    public static Scores getInstance() {
        return instance;
    }

    // TODO 5! : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        URL resource = CsvScores.class.getResource("/data/score.csv");

        if(resource == null) {
            throw new NullPointerException("파일이 없음");
        }

        try{
            List<String> data = Files.readAllLines(Paths.get(resource.toURI()));

            for(String s : data) {
                String temp [] = s.split(",");
                this.scoresRepository.add(new Score(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        scoresRepository.forEach(System.out::println);
    }

    @Override
    public List<Score> findAll() {
        return this.scoresRepository;
    }
}
