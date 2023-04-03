package com.soytn1ce.serdemo.proxy;

/**
 * 静态代理
 * Proxy:Logging before request
 * RealSubject:handing request
 * Proxy:Logging after request
 *
 * 通过代理类proxy调用RealSubject
 * 代理类需要实现原始类实现相同接口，将方法调用转发给原始类
 * 代码量大
 */
public class StaticProxy {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);
        proxy.request();
    }
}

