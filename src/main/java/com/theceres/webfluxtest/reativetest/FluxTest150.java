package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class FluxTest150 {
    public static void run() {
        int bucketSize = 5;
        log.info("##########");
        Flux.range(1, 500)
                .index()
                .scan(
                        new int[bucketSize],
                        (acc, elem) -> {
                            System.out.println(elem);
                            acc[(int) (elem.getT1() % bucketSize)] = elem.getT2();
                            return acc;
                        }
                )
                .skip(bucketSize)
                .map(array -> Arrays.stream(array).sum() * 1.0 / bucketSize)
                .subscribe(result -> log.info(result.toString()));
        log.info("##########");
    }
}
