package com.example.demo.dubbo;

import java.text.SimpleDateFormat;

public class DemoServiceImpl implements DemoService {

    @Override
    public String hello(String name) {
        System.out.println("hello");
        return "hello";
    }
}