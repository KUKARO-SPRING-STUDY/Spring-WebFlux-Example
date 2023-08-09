package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class DoOnNextTest {
    public static void main(String[] args) {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .map(value -> {
                    log.info("first map : " + value);
                    return String.valueOf(value * 10);
                })
                .doOnNext(log::info)
                .map(value -> {
                    log.info("second map : " + value);
                    return value + " toString";
                })
                .doOnNext(log::info)
                .subscribe(value -> {
                    log.info("################# subscribe " + value);
                });
    }
}
