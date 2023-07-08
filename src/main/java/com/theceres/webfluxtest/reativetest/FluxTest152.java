package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class FluxTest152 {
    public static void run() {
        log.info("##########");
        Flux.concat(
                        Flux.range(1, 3),
                        Flux.range(4, 2),
                        Flux.range(6, 5)
                )
                .map(num -> {
                    log.info(num.toString());
                    return num;
                })
                .subscribe(e -> log.info("onNext: {}", e));
        log.info("##########");
    }
}
