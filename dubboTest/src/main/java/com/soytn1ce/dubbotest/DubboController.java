package com.soytn1ce.dubbotest;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class DubboController {
    @Reference
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name")String name){
        String line = "";
        try {
            File file = new File("D:/poc.ser");
            String content = new String(Files.readAllBytes(file.toPath()));
            System.out.println(content);
            helloService.hello(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return helloService.hello(line);
    }
}
