package com.springcloud.ms.controller.pattern;

public class PatternTest {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello, Generics!");
        System.out.println(stringBox.get());

        Box<Integer> integerBox = new Box<>();
        integerBox.set(123);
        System.out.println(integerBox.get());
    }
}
