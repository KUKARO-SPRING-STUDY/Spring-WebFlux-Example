package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
public class FluxTest2Controller {
    @GetMapping("/test2")
    public Mono<String> test2() {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .elapsed()
                .doOnNext(tuple -> log.info("First Time taken: " + tuple.getT1() + " ms, Value: " + tuple.getT2()))
                .elapsed()
                .doOnNext(tuple -> log.info("Second Time taken: " + tuple.getT1() + " ms, Value: " + tuple.getT2()))
                .subscribe();
        return Mono.just("test2");
    }
}
