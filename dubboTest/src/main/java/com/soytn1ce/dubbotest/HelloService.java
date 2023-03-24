package com.soytn1ce.dubbotest;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

public interface HelloService {


    String hello(@PathVariable("name") String name);
}
