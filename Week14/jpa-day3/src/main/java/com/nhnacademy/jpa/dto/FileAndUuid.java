package com.nhnacademy.jpa.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class FileAndUuid {
    private Long fileId;
    private String fileName;
    private String uuidName;

    @QueryProjection
    public FileAndUuid(Long fileId, String fileName, String uuidName) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.uuidName = uuidName;
    }
}
