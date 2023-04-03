package com.soytn1ce.serdemo.proxy;

public class Proxy implements Subject {
    private RealSubject realSubject;
    public Proxy(RealSubject realSubject){this.realSubject=realSubject;}
    @Override
    public void request() {
        System.out.println("Proxy:Logging before request");
        realSubject.request();
        System.out.println("Proxy:Logging after request");
    }
}
