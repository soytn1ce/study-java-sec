package com.example.demo.controller;

import com.example.demo.dao.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

@RestController
@EnableOpenApi // 也可以不写此注解
@Api(description="test controller")
public class TestController {

    @ApiOperation(value = "获取用户信息")
    @GetMapping(value = "/user")
    public User getUser(){
        return new User("mike","man",1);
    }
    @ApiOperation("可以指定参数的api")
    @PostMapping("/param")
    public String hello(@ApiParam("用户名")String name){
        return "hello" + name;
    }
}
