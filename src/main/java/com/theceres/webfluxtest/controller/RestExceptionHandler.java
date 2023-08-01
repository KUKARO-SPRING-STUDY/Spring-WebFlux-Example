package com.theceres.webfluxtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handle(IOException ex) {
        System.out.println("!@!@!");
        System.out.println(ex);
        return notFound().build();
    }
}
