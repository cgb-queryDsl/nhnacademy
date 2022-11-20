package com.nhnmart.cs.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("Post Not Found : " + postId);
    }
}
