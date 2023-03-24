package com.example.springspel.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Ariticle {
    @RequestMapping("/test")
    public String hello(String id){
        int total = 100;
        String message = String.format("read %s books , %d left",id, total-Integer.valueOf(id));

        return message;

       // try{
         //   return message;
        //}catch (Exception e){
          //  throw new IllegalStateException(id);
        //}

    }
}
