package com.theceres.webfluxtest.controller.serverresponse;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@RequiredArgsConstructor
public class ServerResponseConfiguration {

    private final ServerResponseTestController serverResponseTestController;
    @Bean
    public RouterFunction<?> serverResponseRouterFunction() {
        return RouterFunctions
                .route(GET("/res/sr"), request -> serverResponseTestController.serverResponseMono());
    }
}
