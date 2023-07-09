package com.theceres.webfluxtest.reativetest.create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoDeferTest {
    static int a = 5;

    public static void run() {
        var monoJust = Mono.just(a);
        var monoDefer = Mono.defer(() -> Mono.just(a));
        monoJust.subscribe(System.out::println);
        monoDefer.subscribe(System.out::println);

        a = 7;
        monoJust.subscribe(System.out::println);
        monoDefer.subscribe(System.out::println);
    }
}
