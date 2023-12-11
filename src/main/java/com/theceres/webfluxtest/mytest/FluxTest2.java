package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class FluxTest2 {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .timed()
                .doOnNext(tuple -> System.out.println("First Time taken: " + tuple.elapsed().toMillis() + " ms, Value: " + tuple.get()))
                .timed()
                .doOnNext(tuple -> System.out.println("Second Time taken: " + tuple.elapsed().toMillis() + " ms, Value: " + tuple.get()))
                .subscribe();
    }
}
