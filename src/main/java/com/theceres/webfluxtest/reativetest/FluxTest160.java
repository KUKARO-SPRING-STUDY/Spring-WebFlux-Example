package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class FluxTest160 {
    public static void run() {
        log.info("##########");
        Flux.just(1, 2, 3)
                .concatWith(Flux.just(4, 5, 6))
                .doOnEach(s -> log.info("signal: {}", s))
                .subscribe(result -> log.info("result: {}", result));
        log.info("##########");
        Flux.just(1, 2, 3)
                .concatWith(Flux.error(new RuntimeException("Conn error")))
                .doOnEach(s -> log.info("signal: {}", s))
                .subscribe();
        log.info("##########");
        Flux.range(1, 3)
                .doOnNext(e -> log.info("data : {}", e))
                .materialize()
                .doOnNext(s -> log.info("signal : {}", s))
                .dematerialize()
                .collectList()
                .subscribe(r -> log.info("result : {}", r));
        log.info("##########");
    }
}
