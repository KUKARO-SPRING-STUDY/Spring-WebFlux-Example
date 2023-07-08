package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FluxTest145 {
    public static void run() {
        log.info("##########");
        Flux.range(2018, 5)
                .subscribe(result->log.info(result.toString()));
        log.info("##########");
        Flux.range(2018, 5)
                .timestamp()
                .subscribe(result->log.info(result.toString()));
        log.info("##########");
        Flux.range(2018, 5)
                .timestamp()
                .index()
                .subscribe(result->log.info(result.toString()));
        log.info("##########");
    }
}
