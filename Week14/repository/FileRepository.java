package com.nhnacademy.jdbc.board.repository;

import com.nhnacademy.jdbc.board.domain.dto.FileNameAndUuid;
import com.nhnacademy.jdbc.board.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<FileNameAndUuid> findAllByPostId(Long postId);
    FileNameAndUuid getFileByFileId(Long fileId);
}
