package com.theceres.webfluxtest.reativetest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxTest153 {
    public static void run() {
        log.info("##########");
        Flux.range(1, 13)
                .buffer(4)
                .subscribe(e -> log.info("onNext: {}", e));
        log.info("##########");
        var windowedFlux = Flux.range(100, 20)
                .windowUntil(FluxTest153::isPrime, true);
        windowedFlux.subscribe(window -> window
                .collectList()
                .subscribe(e -> log.info("window: {}", e)));
        log.info("##########");
    }

    public static boolean isPrime(int num) {
        boolean isPrime = false;
        for (int i = 2; i <= num; i++) {
            if (num == i) {
                isPrime = true;
                break;
            }
            if (num % i == 0) {
                break;
            }
        }
        return isPrime;
    }
}
