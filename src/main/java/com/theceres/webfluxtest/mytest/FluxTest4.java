package com.theceres.webfluxtest.mytest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluxTest4 {
    public static void main(String[] args) {
        var a = Stream.of(1, 2, 3);
        a.collect(Collectors.toList());
        a.collect(Collectors.toList());
    }
}
