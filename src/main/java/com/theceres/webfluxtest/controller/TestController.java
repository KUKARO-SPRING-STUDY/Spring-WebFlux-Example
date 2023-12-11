package com.theceres.webfluxtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/rtb")
    public Mono<String> rtb(
            ServerHttpRequest httpRequest) {
        log.info("!!!!");
        log.info(httpRequest.getRemoteAddress().toString());
        Thread.currentThread().getThreadGroup().list();
        System.out.println(Runtime.getRuntime().availableProcessors());
        return Mono.just("GGOD");
    }
}
