package com.example.demo.dubbo;

public class DemoServiceImpl implements DemoService {

    @Override
    public String hello(String name) {
        System.out.println("hello");
        return "hello";
    }
}