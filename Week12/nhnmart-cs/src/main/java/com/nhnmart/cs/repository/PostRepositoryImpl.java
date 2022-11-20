package com.nhnmart.cs.repository;

import com.nhnmart.cs.domain.AnswerStatus;
import com.nhnmart.cs.domain.Category;
import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.domain.Post;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@ToString
public class PostRepositoryImpl implements PostRepository {

    private static final Map<Long, Post> postMap = new HashMap<>();

    @Override
    public boolean exits(long id) {
        return postMap.containsKey(id);
    }

    @Override
    public Post register(Customer customer, String title, Category category, String content, String writeTime, String[] files) {
        long id = postMap.keySet()
                .stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l -> l + 1)
                .orElse(1L);

        Post post = new Post(customer, title, category, content, writeTime, files);
        post.setId(id);
        postMap.put(id, post);

        return post;
    }

    @Override
    public Map<Long, Post> getPostMap() {
        return postMap;
    }

    @Override
    public List<Post> getPostList() {
        return new ArrayList<>(postMap.values());
    }

    @Override
    public Post getPost(long id) {
        return exits(id) ? postMap.get(id) : null;
    }

    @Override
    public List<Post> getPostListByCustomer(String customerId) {
        List<Post> postList = getPostList().stream()
                .sorted(Comparator.comparing((Post p) -> p.getWriteTime()).reversed())
                .filter(x -> x.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
        return postList;
    }

    @Override
    public List<Post> getPostListYetAnswer() {
        List<Post> postList = getPostList().stream()
                .filter(x -> x.getAnswerStatus() == AnswerStatus.YET)
                .collect(Collectors.toList());

        return postList;
    }

    @Override
    public List<Post> getPostListByCategory(Category category) {
        List<Post> postList = getPostList().stream()
                .filter(x -> x.getCategory().equals(category) && x.getAnswerStatus() == AnswerStatus.YET)
                .collect(Collectors.toList());

        return postList;
    }
}
