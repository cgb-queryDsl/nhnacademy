package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.BootStrap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BootStrap.class})
class CsvDataParserTest {

    @Autowired
    private DataParser dataParser;
    private List<String> data;

    @Test
    void parse() {
        String path = "/data/Tariff_20220331.csv";

        data = new ArrayList<>();
        data = dataParser.parse(path);

        Assertions.assertThat(data.size()).isEqualTo(100);
    }

    @Test
    void parseInvalidPath() {
        String path = "Invalid Path";

        Assertions.assertThatThrownBy(() -> dataParser.parse(path))
                .isInstanceOf(NullPointerException.class);
    }
}
