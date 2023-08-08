package com.theceres.webfluxtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class Test2Controller {

    @GetMapping("/test")
    public Mono<String> test(
            ServerHttpRequest httpRequest) {
        return throwError();
    }

    public Mono<Boolean> returnBoolean() {
        return Mono.just(true);
    }

    public Mono<String> throwError() {
        log.info("throwError");
        try {
            return returnBoolean()
                    .handle((it, sink) -> {
                        sink.error(new RuntimeException("MI ERROR"));
                    })
                    .onErrorReturn("OOPS")
                    .map(Object::toString);
        }catch (Exception e){
            return success();
        }
    }

    public Mono<String> success(){
        log.info("SUCCESS");
        return Mono.just("SUCCESS");
    }
}
