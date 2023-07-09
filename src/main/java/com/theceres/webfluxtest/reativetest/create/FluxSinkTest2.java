package com.theceres.webfluxtest.reativetest.create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * <a href="https://javacan.tistory.com/entry/Reactor-Start-3-RS-create-stream">...</a>
 */
@Slf4j
public class FluxSinkTest2 {
    public static void run() {
        log.info("##########");
        Flux.create(integerFluxSink -> {
                    integerFluxSink.onRequest(request -> {
                        System.out.println("GOOD");
                    });
                })
                .take(10)
                .doOnEach(objectSignal -> {
                    log.info("doOnEach: {}", objectSignal);
                })
                .doOnNext(value -> {
                    log.info("doOnNext: {}", value);
                })
                .subscribe(value -> {
                    log.info("subscribe: {}", value);
                });
        log.info("##########");
    }
}
