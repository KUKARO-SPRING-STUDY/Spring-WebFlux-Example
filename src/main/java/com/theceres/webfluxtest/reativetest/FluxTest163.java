package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;

@Slf4j
public class FluxTest163 {
    public static void run() {
        log.info("##########");
        Flux.generate(
                        () -> Tuples.of(0L, 1L),
                        (state, sink) -> {
                            log.info("generated value: {}", state.getT2());
                            sink.next(state.getT2());
                            long newValue = state.getT1() + state.getT2();
                            return Tuples.of(state.getT2(), newValue);
                        })
                .delayElements(Duration.ofMillis(1))
                .take(20)
                .subscribe(e -> log.info("onNext: {}", e));
        log.info("##########");
    }
}
