package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FileNameAndUuid {

    private final Long fileId;
    private final String fileName;
    private final String uuidName;

    public FileNameAndUuid(Long fileId, String fileName, String uuidName) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.uuidName = uuidName;
    }
}
