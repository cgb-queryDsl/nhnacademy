package com.nhnacademy.jdbc.board.service.impl;

import com.nhnacademy.jdbc.board.domain.dto.FileNameAndUuid;
import com.nhnacademy.jdbc.board.domain.dto.InsertFile;
import com.nhnacademy.jdbc.board.mapper.FileMapper;
import com.nhnacademy.jdbc.board.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    @Transactional
    public void registerFile(Long postId, String originFileName) {
        fileMapper.insertFile(new InsertFile(postId, originFileName));
    }

    @Override
    @Transactional(readOnly = true)
    public int lastInsertFileId() {
        return fileMapper.getLastInsertFileId();
    }

    @Override
    public List<FileNameAndUuid> getFiles(Long postId) {
        return fileMapper.getFiles(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public FileNameAndUuid getFile(Long fileId) {
        return fileMapper.getFile(fileId);
    }
}
