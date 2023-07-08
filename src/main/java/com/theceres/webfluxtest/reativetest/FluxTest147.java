package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@Slf4j
public class FluxTest147 {
    public static void run() {
        log.info("##########");
        Flux.just(1, 6, 3, 8, 3, 1, 5, 1)
                .collectSortedList(Comparator.reverseOrder())
                .subscribe(result -> log.info(result.toString()));
        log.info("##########");
    }
}
