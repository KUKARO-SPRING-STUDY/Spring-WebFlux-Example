package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FluxTest {
    public static void main(String[] args) {
        Flux.fromIterable(List.of(1,2,3,4)).map(integer -> {
            log.info(integer+"");
            return integer;
        }).subscribe();
    }
}
