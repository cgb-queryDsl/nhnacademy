package com.nhnacademy.board.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostsRepository implements PostRepository {

    private List<Post> postRepository;

    public PostsRepository() {
        this.postRepository = new ArrayList<>();
    }

    @Override
    public long register(Post post) {
        this.postRepository.add(post);

        return post.getId();
    }

    @Override
    public void modify(Post post) {
        for (int i = 0; i < this.postRepository.size(); i++) {
            if(this.postRepository.get(i).getId() == post.getId()) {
                this.postRepository.set(i, post);
                break;
            }
        }
    }

    @Override
    public Post remove(long id) {
        Post returnPost = null;
        for (int i = 0; i < this.postRepository.size(); i++) {
            if(this.postRepository.get(i).getId() == id) {
                returnPost = this.postRepository.remove(i);
                break;
            }
        }

        return returnPost;
    }

    @Override
    public Post getPost(long id) {
        List<Post> list = this.postRepository.stream().filter((x) -> x.getId() == id)
                .collect(Collectors.toList());

        if(list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Post> getPosts() {
        return this.postRepository;
    }

    @Override
    public String toString() {
        return "PostsRepository{" +
                "postRepository=" + postRepository +
                '}';
    }
}
