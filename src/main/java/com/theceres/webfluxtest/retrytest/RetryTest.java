package com.theceres.webfluxtest.retrytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@RestController
public class RetryTest {
    @GetMapping("/retry")
    public Mono<String> test(){
        return WebClient.create("http://localhost:8080").get()
                .uri("/err")
                .retrieve()
                .bodyToMono(String.class)
//                .exchangeToMono(value->{
//                    log.info(value+"");
//                    log.info(value.headers().asHttpHeaders()+"");
//                    return value.bodyToMono(String.class);
//                })

//                .timeout(Duration.ofSeconds(1L))
//                .retryWhen(Retry.fixedDelay(3L, Duration.ofSeconds(3L))
////                        .filter(this::is5xxServerError)
//                        .doBeforeRetry(it->{
//                    log.info("doBeforeRetry");
//                    log.info(it+"");
//                }))
                .retry(10)
                .doOnNext(it->{
                    log.info("doOnNext");
                    log.info(it);
                });
    }

    private boolean is5xxServerError(Throwable throwable) {
        System.out.println(":!@!@!@");
        return throwable instanceof WebClientResponseException &&
                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError();
    }
}
