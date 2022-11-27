package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.dto.FileNameAndUuid;
import com.nhnacademy.jdbc.board.domain.dto.InsertFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFile(InsertFile insertFile);
    int getLastInsertFileId();
    List<FileNameAndUuid> getFiles(Long postId);
    FileNameAndUuid getFile(Long fileId);
}
