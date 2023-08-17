package com.theceres.webfluxtest.normaljava;

public class SwitchExpression {
    public static void main(String[] args) {
        int i = 1;
        String answer = switch (i) {
            case 1 -> "one";
            case 2 -> "two";
            default -> "other";
        };
        System.out.println(answer);
    }
}
