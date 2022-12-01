package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.dto.FileAndUuid;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FileRepositoryCustom {
    List<FileAndUuid> getFiles(Long postId);
    FileAndUuid getFile(Long fileId);
}
