package com.theceres.webfluxtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.notFound;

@Slf4j
@RestController
public class ErrorController {

    @GetMapping("/err")
    public Mono<String> error(
            ServerHttpRequest httpRequest) {
        return Mono.just("HELLO")
                .map(s -> {
                    log.info("BEFORE EXCEPTION");
                    throw new PostNotFoundException(1234L);
                })
                .map(e -> {
                    return e + "";
                });
    }
}
