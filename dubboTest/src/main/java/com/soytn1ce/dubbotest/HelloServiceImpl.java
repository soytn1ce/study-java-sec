package com.soytn1ce.dubbotest;

import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.rpc.filter.GenericFilter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class HelloServiceImpl implements HelloService{
    static {
            String line = "";
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
                File file = new File("D:/poc.ser");
                String content = new String(Files.readAllBytes(file.toPath()));
                GenericFilter genericFilter = new GenericFilter();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @Override
    public String hello(String name) {
        System.out.println("hello:" + name);
        return "hello";
    }
}
