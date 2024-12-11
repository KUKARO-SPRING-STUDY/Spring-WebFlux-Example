package com.theceres.webfluxtest.controller;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Consumer;

import static java.util.function.Function.identity;

@Slf4j
@RestController
@AllArgsConstructor
public class TestController {

    private final WebClient.Builder builder;

    @GetMapping("/rtb")
    public Mono<String> rtb(
            ServerHttpRequest httpRequest) {
        log.info("!!!!");
        log.info(httpRequest.getRemoteAddress().toString());
        Thread.currentThread().getThreadGroup().list();
        System.out.println(Runtime.getRuntime().availableProcessors());
        return Mono.just("GGOD");
    }

    @GetMapping("/retry1")
    public Mono<String> retry() {
        final HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .compress(true)
                .metrics(false, identity())
//                .disableRetry(true)
                ;

        final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .maxInMemorySize(4 * 1024 * 1024))
                .build();
        final WebClient webClient = builder
                .clientConnector(connector)
                .exchangeStrategies(exchangeStrategies)
                .filter(logRequest())
                .build()
                .mutate()
                .baseUrl("http://localhost:8080")
                .defaultHeaders(Header.applicationJsonUtf8Header())
                .build();
        var id = 1;
        return webClient.get()
                .uri("/test10?id=" + id)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(1))
                .retryWhen(Retry.max(100).doBeforeRetry(retrySignal -> {
                    log.info("retrySignal: {}", retrySignal);
                }));
    }

    public ExchangeFilterFunction logRequest() {
        return ((request, next) -> {
            request.headers().forEach(((name, values) -> {
                log.info(" - headers: {}: {}", name, values);
            }));

            long startTime = System.currentTimeMillis();

            return next.exchange(request)
                    .doOnNext((ClientResponse response) -> {
                        long elapsed = System.currentTimeMillis() - startTime;
                        if (elapsed > 1000) {
                            log.warn("[Request Delay] url: {},  method: {}, status: {}, elapsed: {}ms", request.url(), request.method(), response.statusCode(), elapsed);
                        }
                        log.info("[Request] url: {},  method: {}, status: {}, elapsed: {}ms", request.url(), request.method(), response.statusCode(), elapsed);
                    });
        });
    }
}

class Header {
    public static Consumer<HttpHeaders> applicationJsonUtf8Header() {
        return httpHeaders -> {
            httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.add("Accept-Charset", "utf-8");
        };
    }
}
