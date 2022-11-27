package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.dto.FileNameAndUuid;

import java.util.List;

public interface FileService {
    void registerFile(Long postId, String originFileName);
    int lastInsertFileId();
    List<FileNameAndUuid> getFiles(Long postId);
    FileNameAndUuid getFile(Long fileId);
}
