package com.theceres.webfluxtest.controller.serverresponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
public class ServerResponseTestController {

    public Mono<ServerResponse> serverResponseMono() {
        System.out.println("!@!@!");
        return Mono.<ServerResponse>justOrEmpty(null)
                .switchIfEmpty(ServerResponse.ok().build());
//        return Mono.empty();
    }

    @RequestMapping("/res/void")
    public Mono<Void> voidMono() {
        return Mono.empty();
    }
}
