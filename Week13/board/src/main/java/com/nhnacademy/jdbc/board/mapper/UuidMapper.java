package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.dto.InsertUuid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UuidMapper {
    void insertUuid(InsertUuid insertUuid);
}
