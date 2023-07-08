package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Random;

@Slf4j
public class FluxTest156 {
    public static void run() {
        log.info("##########");
        Flux.just("user-1", "user-2", "user-3")
                .flatMap(u -> requestBooks(u)
                        .map(b -> u + "/" + b))
                .subscribe(r -> log.info("onNext: {}", r));
        log.info("##########");
    }

    public static Flux<String> requestBooks(String user) {
        return Flux.range(1, new Random().nextInt(3) + 1)
                .map(i -> "book-" + i)
//                .delayElements(Duration.ofMillis(3))
                ;
    }
}
