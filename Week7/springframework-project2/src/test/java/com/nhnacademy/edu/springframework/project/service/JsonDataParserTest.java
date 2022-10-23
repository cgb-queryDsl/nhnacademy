//package com.nhnacademy.edu.springframework.project.service;
//
//import com.nhnacademy.edu.springframework.project.BootStrap;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {BootStrap.class})
//class JsonDataParserTest {
//
//    @Autowired
//    private DataParser dataParser;
//    private List<String> data;
//
//    @Test
//    void parse() {
//        String path = "data/Tariff_20220331.json";
//
//        data = new ArrayList<>();
//        data = dataParser.parse(path);
//
//        Assertions.assertThat(data.size()).isEqualTo(99);
//    }
//
//    @Test
//    void parseInvalidPath() {
//        String path = "Invalid Path";
//
//        Assertions.assertThatThrownBy(() -> dataParser.parse(path))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//}
