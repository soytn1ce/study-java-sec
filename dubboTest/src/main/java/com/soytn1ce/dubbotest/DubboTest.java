package com.soytn1ce.dubbotest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        System.out.println("Dubbo service started.");
    }
}
