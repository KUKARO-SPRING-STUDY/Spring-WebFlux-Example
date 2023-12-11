package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
public class FluxTest3Controller {
    @GetMapping("/test3")
    public Mono<String> test3() {ㅊ
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .timed()
                .doOnNext(tuple -> log.info("First Time taken: " + tuple.elapsed().toMillis() + " ms, Value: " + tuple))
                .timed()
                .doOnNext(tuple -> log.info("Second Time taken: " + tuple.elapsed().toMillis() + " ms, Value: " + tuple.get()))
                .subscribe();
        return Mono.just("test3");
    }
}
