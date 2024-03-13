package com.theceres.webfluxtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class Test2Controller {
    private static AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/test")
    public Mono<String> test(
            @RequestParam String id,
            ServerHttpRequest httpRequest) {
        try {
            log.info(counter.incrementAndGet() + ":" + id);
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.just("TEST");
    }

    @GetMapping("/test5")
    public Mono<String> test5(
            @RequestParam String id,
            ServerHttpRequest httpRequest) {
        try {
            log.info(counter.incrementAndGet() + ":" + id);
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.just("TEST5");
    }

    @GetMapping("/test10")
    public Mono<String> test10(
            @RequestParam String id,
            ServerHttpRequest httpRequest) {
        try {
            log.info(counter.incrementAndGet() + ":" + id);
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.just("TEST10");
    }

    public Mono<Boolean> returnBoolean() {
        return Mono.just(true);
    }

    public Mono<String> throwError() {
        log.info("throwError");
        try {
            return returnBoolean()
                    .handle((it, sink) -> {
                        sink.error(new RuntimeException("MI ERROR"));
                    })
                    .onErrorReturn("OOPS")
                    .map(Object::toString);
        } catch (Exception e) {
            return success();
        }
    }

    public Mono<String> success() {
        log.info("SUCCESS");
        return Mono.just("SUCCESS");
    }
}
