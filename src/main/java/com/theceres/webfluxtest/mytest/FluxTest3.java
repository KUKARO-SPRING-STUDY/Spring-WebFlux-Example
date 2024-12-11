package com.theceres.webfluxtest.mytest;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

import static java.util.function.Function.identity;

@Slf4j
public class FluxTest3 {
    public static void main(String[] args) {
        var id = 1;
        final HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .compress(true)
                .metrics(false, identity());

        final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .maxInMemorySize(4 * 1024 * 1024))
                .build();

        var mono = WebClient.create("http://localhost:8080").get()
                .uri("/test10?id=" + id)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(1))
                .retryWhen(Retry.max(100).doBeforeRetry(retrySignal -> {
                    log.info("retrySignal: {}", retrySignal);
                }));
        mono.subscribe();
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
