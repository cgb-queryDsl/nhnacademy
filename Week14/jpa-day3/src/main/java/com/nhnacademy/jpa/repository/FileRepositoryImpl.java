package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.dto.FileAndUuid;
import com.nhnacademy.jpa.dto.QFileAndUuid;
import com.nhnacademy.jpa.entity.File;
import com.nhnacademy.jpa.entity.QFile;
import com.nhnacademy.jpa.entity.QUuid;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FileRepositoryImpl extends QuerydslRepositorySupport implements FileRepositoryCustom {

    public FileRepositoryImpl() {
        super(File.class);
    }

    @Override
    public List<FileAndUuid> getFiles(Long postId) {
        QFile file = QFile.file;
        QUuid uuid = QUuid.uuid;

        return from(file)
                .innerJoin(file.uuid, uuid)
                .select(new QFileAndUuid(
                        file.id,
                        file.fileName,
                        uuid.uuidValue))
                .where(file.post.id.eq(postId))
                .fetch();
    }

    @Override
    public FileAndUuid getFile(Long fileId) {
        QFile file = QFile.file;
        QUuid uuid = QUuid.uuid;

        return from(file)
                .innerJoin(file.uuid, uuid)
                .select(new QFileAndUuid(
                        file.id,
                        file.fileName,
                        uuid.uuidValue))
                .where(file.id.eq(fileId))
                .fetchOne();
    }
}
