package com.theceres.webfluxtest.controller;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Post:" + id +" is not found.");
    }
}
