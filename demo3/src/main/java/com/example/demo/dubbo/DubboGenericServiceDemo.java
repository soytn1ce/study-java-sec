package com.example.demo.dubbo;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.ArrayList;

public class DubboGenericServiceDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/provider.xml");
        context.start();
        System.out.println("Dubbo service started.");

    }
}

