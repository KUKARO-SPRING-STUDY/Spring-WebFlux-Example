package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FluxTest6 {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(i -> {
                    // 복잡한 계산
                    try {
                        Thread.sleep(1000);
                        log.info("runOn : " + i);
                        return i;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sequential()
                .subscribe((i) -> {
                    log.info("subscribe : " + i);
                });
        Thread.sleep(10000L);
    }
}

