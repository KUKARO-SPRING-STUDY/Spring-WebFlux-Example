package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;

@Slf4j
public class FluxTest162 {
    public static void run() {
        log.info("##########");
//        Flux.push(fluxSink -> IntStream
//                        .range(2000, 3000)
//                        .forEach(fluxSink::next))
//                .delayElements(Duration.ofMillis(1))
//                .subscribe(e -> log.info("onNext: {}", e));
        log.info("##########");
        Flux.create(fluxSink -> fluxSink.onDispose(() -> log.info("Disposed")))
                .subscribe(e -> log.info("onNext: {}", e));
        log.info("##########");
    }
}
