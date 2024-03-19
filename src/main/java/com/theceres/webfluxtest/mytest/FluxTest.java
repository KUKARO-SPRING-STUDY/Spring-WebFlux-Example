package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Timed;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping("/flux-test")
public class FluxTest {
    @GetMapping("")
    public Mono<String> get() {
        // alter table creative add column mobile_encoded_url text;
        // alter table creative add column pc_encoded_url text;
        return Flux.just(no1("1"), no1("2"), no3("3"), no2("4"), no2("5"))
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
                    String s = "(" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .publishOn(Schedulers.boundedElastic())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<String> no1(String id) {
        return Mono.fromSupplier(() -> {
                    log.info("supplier : {}", id);
                    return id;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test?id=" + id)
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSubscribe(value2 -> {
                                log.info("inner subscribe : {}", id);
                            })
                            .doOnCancel(() -> {
                                log.info("inner {} cancel", id);
                            });
                })
                .doOnCancel(() -> {
                    log.info("{} cancel", id);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s = "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test10?id=" + id)
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSubscribe(value2 -> {
                                log.info("inner subscribe : {}", id);
                            })
                            .doOnCancel(() -> {
                                log.info("inner {} cancel", id);
                            });
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s = "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .doOnSubscribe(value -> {
                    log.info("subscribe : {}", id);
                })
                .doOnCancel(() -> {
                    log.info("{} cancel", id);
                });
    }

    public Mono<String> no2(String id) {
        return Mono.fromSupplier(() -> {
                    log.info("supplier : {}", id);
                    return id;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test5?id=" + id)
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSubscribe(value2 -> {
                                log.info("inner subscribe : {}", id);
                            })
                            .doOnCancel(() -> {
                                log.info("inner {} cancel", id);
                            });
                })
                .doOnCancel(() -> {
                    log.info("{} cancel", id);
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s = "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test?id=" + id)
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSubscribe(value2 -> {
                                log.info("inner subscribe : {}", id);
                            })
                            .doOnCancel(() -> {
                                log.info("inner {} cancel", id);
                            });
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s = "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .doOnSubscribe(value -> {
                    log.info("subscribe : {}", id);
                })
                .doOnCancel(() -> {
                    log.info("{} cancel", id);
                });
    }

    public Mono<String> no3(String id) {
        return Mono.fromSupplier(() -> {
                    log.info("supplier : {}", id);
                    return id;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test10?id=" + id)
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSubscribe(value2 -> {
                                log.info("inner subscribe : {}", id);
                            })
                            .doOnCancel(() -> {
                                log.info("inner {} cancel", id);
                            });
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s = "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .flatMap((value) -> {
                    log.info(value);
                    return WebClient.create("http://localhost:8080").get()
                            .uri("/test5?id=" + id)
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSubscribe(value2 -> {
                                log.info("inner subscribe : {}", id);
                            })
                            .doOnCancel(() -> {
                                log.info("inner {} cancel", id);
                            });
                })
                .map(result -> result + id)
                .timed()
                .map(timed -> {
                    String s = "(" + id + ":" + timed.get() + ":" + timed.elapsed().toMillis() + "ms" + ")";
                    log.info(s);
                    return s;
                })
                .doOnSubscribe(value -> {
                    log.info("subscribe : {}", id);
                })
                .doOnCancel(() -> {
                    log.info("{} cancel", id);
                });
    }
}
