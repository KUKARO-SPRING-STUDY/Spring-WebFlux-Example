package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Slf4j
public class FluxTest158 {
    public static void run() {
        log.info("##########");
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(1))
                .sample(Duration.ofMillis(10))
                .subscribe(e -> log.info("onNext: {}", e));
        log.info("##########");
    }
}
