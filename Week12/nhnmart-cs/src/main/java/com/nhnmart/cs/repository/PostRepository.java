package com.nhnmart.cs.repository;

import com.nhnmart.cs.domain.Category;
import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.domain.Post;

import java.util.List;
import java.util.Map;

public interface PostRepository {

    Post register(Customer customer, String title, Category category, String content, String writeTime, String[] files);
    Map<Long, Post> getPostMap();
    boolean exits(long id);
    List<Post> getPostList();
    Post getPost(long id);
    List<Post> getPostListByCustomer(String customerId);
    List<Post> getPostListYetAnswer();
    List<Post> getPostListByCategory(Category category);
}
