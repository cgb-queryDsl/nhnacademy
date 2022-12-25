package com.nhnacademy.edu.finaltest.service.impl;

import com.nhnacademy.edu.finaltest.exception.FileReadException;
import com.nhnacademy.edu.finaltest.service.FileParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FileParserImpl implements FileParser {

    private static List<String> data;

    @Override
    public List<String> parse(String path) {
        data = new ArrayList<>();

        try {
            data = Files.readAllLines(Paths.get(path));
        } catch (IOException | InvalidPathException e) {
            log.error("error = {}", e);
            throw new FileReadException(path);
        }

        return data;
    }
}
