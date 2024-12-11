package com.theceres.webfluxtest.mytest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FluxTest7 {
    public static void main(String[] args) {
        // Flux 정의
        var a = Flux.range(1, 10)
                // publishOn으로 병렬 스케줄러 사용 지정
//                .publishOn(Schedulers.parallel())
                // 각 데이터에 대해 연산 수행
                .subscribeOn(Schedulers.boundedElastic())
                .map(i -> {
                    // 복잡한 계산
                    try {
                        Thread.sleep(1000L);
                        log.info("runOn : " + i);
                        return i;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        var b = Flux.range(1, 10)
                // publishOn으로 병렬 스케줄러 사용 지정
//                .publishOn(Schedulers.parallel())
                // 각 데이터에 대해 연산 수행
                .map(i -> {
                    // 복잡한 계산
                    try {
                        Thread.sleep(1000L);
                        log.info("runOn : " + i);
                        return i;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        a.subscribe((i) -> {
            log.info("subscribe : " + i);
        });
        a.subscribe((i) -> {
            log.info("subscribe : " + i);
        });
        b.subscribe((i) -> {
            log.info("subscribe : " + i);
        });
        // Scheduler가 비동기로 동작하기 때문에 main thread가 종료되지 않도록 잠시 대기
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

