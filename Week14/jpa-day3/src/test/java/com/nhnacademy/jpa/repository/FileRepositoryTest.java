package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.FileAndUuid;
import com.nhnacademy.jpa.entity.File;
import com.nhnacademy.jpa.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class FileRepositoryTest {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    void insertFile() {
        File file = new File();
        file.setId(1l);
        file.setFileName("file1");

        Optional<Post> post = postRepository.findById(1l);
        file.setPost(post.get());

        fileRepository.save(file);
        fileRepository.flush();
    }

    @Test
    void getFiles() {
        List<FileAndUuid> files = fileRepository.getFiles(1l);

        Assertions.assertThat(files.size()).isEqualTo(2);
    }

    @Test
    void getFile() {
        FileAndUuid file = fileRepository.getFile(1l);

        Assertions.assertThat(file.getFileName()).isEqualTo("a.txt");
        Assertions.assertThat(file.getUuidName()).isEqualTo("aszc0-29d.txt");
    }
}