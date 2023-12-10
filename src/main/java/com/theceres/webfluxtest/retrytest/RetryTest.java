package com.theceres.webfluxtest.retrytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class RetryTest {
    public Mono<String> test(){
        WebClient.create("https://keywordad-admin.sandbox.onkakao.net").post()
                .uri("/signin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .exchangeToMono(value->{
                    log.info(value+"");
                    log.info(value.headers().asHttpHeaders()+"");
                    return value.bodyToMono(String.class);
                });
    }
}
