package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class FluxTest {
    public static void run() {
        Flux.range(1, 100)
//                .repeat()
                .flatMap(integer -> Flux.just(List.of(integer, integer * 2)))
                .collectList()
                .subscribe(integers -> log.info(integers.toString()));
    }
}
