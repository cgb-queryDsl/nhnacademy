package com.nhnmart.cs.domain.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Value
public class CustomerPostRegisterRequest {
    @NotEmpty
    @Size(max = 200)
    String title;

    @NotEmpty
    String category;

    List<MultipartFile> file;

    @NotEmpty
    @Size(max = 40000)
    String content;
}
