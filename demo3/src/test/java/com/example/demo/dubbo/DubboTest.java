package com.example.demo.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo/provider.xml"});
        context.start();
        System.in.read();
    }
}
