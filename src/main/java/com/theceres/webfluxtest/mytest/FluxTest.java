package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Timed;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flux-test")
public class FluxTest {
    @GetMapping("")
    public Mono<String> get() {
        return Flux.fromIterable(List.of(no1("1"), no2("2"), no3("3"), no1("4"), no2("5")))
                .timed()
                .switchMap(Timed::get)
                .map(value -> {
                    log.info("########");
                    log.info(value);
                    return value;
                })
                .collectList()
                .map(Object::toString)
                .timed()
                .map(timed -> {
                    String s =  "(" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                });
    }

    public Mono<String> no1(String id) {
        return Mono.just(id)
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test")
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s =  "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test10")
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s =  "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                });
    }

    public Mono<String> no2(String id) {
        return Mono.just(id)
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test5")
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s =  "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test")
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s =  "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                });
    }

    public Mono<String> no3(String id) {
        return Mono.just(id)
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test10")
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                 String s =  "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                 log.info(s);
                    return s;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test5")
                            .retrieve()
                            .bodyToMono(String.class);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s =  "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                });
    }
}
