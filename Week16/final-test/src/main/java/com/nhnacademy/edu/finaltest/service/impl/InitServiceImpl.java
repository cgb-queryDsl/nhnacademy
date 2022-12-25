package com.nhnacademy.edu.finaltest.service.impl;

import com.nhnacademy.edu.finaltest.service.DataSaver;
import com.nhnacademy.edu.finaltest.service.FileParser;
import com.nhnacademy.edu.finaltest.service.InitService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class InitServiceImpl implements InitService {

    private final FileParser fileParser;
    private final DataSaver dataSaver;

    public InitServiceImpl(FileParser fileParser, DataSaver dataSaver) {
        this.fileParser = fileParser;
        this.dataSaver = dataSaver;
    }

    @Override
    public void initialize(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                List<String> parseData = fileParser.parse(filePath);
                dataSaver.dataSave(parseData);
            }
        }
    }
}
