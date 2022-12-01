package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.FileAndUuid;
import com.nhnacademy.jpa.entity.File;
import com.nhnacademy.jpa.entity.Post;
import com.nhnacademy.jpa.entity.Uuid;
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
class UuidRepositoryTest {

    @Autowired
    UuidRepository uuidRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    FileRepository fileRepository;

    @Test
    void insertUuid() {
        Optional<Post> post = postRepository.findById(1l);

        List<File> files = fileRepository.findByPostId(post.get().getId());

        long i = 1l;
        for (File file : files) {
            Uuid uuid = new Uuid();
            uuid.setId(i);
            uuid.setPost(post.get());
            uuid.setFile(file);
            uuidRepository.save(uuid);
            uuidRepository.flush();
            i++;
        }

        List<Uuid> uuids = uuidRepository.findAll();
        Assertions.assertThat(uuids.size()).isEqualTo(files.size());
    }
}