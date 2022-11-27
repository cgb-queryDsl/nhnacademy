package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Value
public class PostRegisterForm {

    String title;
    List<MultipartFile> files;
    String content;
}
