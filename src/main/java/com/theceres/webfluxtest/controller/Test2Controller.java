package com.theceres.webfluxtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class Test2Controller {
    private static int counter = 0;

    @GetMapping("/test")
    public Mono<String> test(
            ServerHttpRequest httpRequest) {
        try {
            counter++;
            log.info(counter + "");
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.just("TEST");
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
