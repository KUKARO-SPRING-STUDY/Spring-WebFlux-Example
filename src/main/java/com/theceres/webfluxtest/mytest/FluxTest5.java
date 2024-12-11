package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class FluxTest5 {
    public static void main(String[] args) {
        var item = Mono.just(1);
        var geo = item.flatMap(atom->{
            return  Mono.just(atom + 1);
        });
        item.zipWith(geo).subscribe(atom->{
            System.out.println(atom);
        });

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
