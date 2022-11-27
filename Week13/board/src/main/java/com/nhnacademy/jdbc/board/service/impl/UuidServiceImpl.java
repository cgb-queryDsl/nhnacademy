package com.nhnacademy.jdbc.board.service.impl;

import com.nhnacademy.jdbc.board.domain.dto.InsertUuid;
import com.nhnacademy.jdbc.board.mapper.UuidMapper;
import com.nhnacademy.jdbc.board.service.UuidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UuidServiceImpl implements UuidService {

    private final UuidMapper uuidMapper;

    public UuidServiceImpl(UuidMapper uuidMapper) {
        this.uuidMapper = uuidMapper;
    }

    @Override
    @Transactional
    public void registerUuid(Long fileId, Long postId, String uuidFileName) {
        uuidMapper.insertUuid(new InsertUuid(fileId, postId, uuidFileName));
    }
}
