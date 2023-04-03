package com.soytn1ce.serdemo.proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject:handing request");
    }
}
