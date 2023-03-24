package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.util.ObjectUtils.isEmpty;


//配置actuator的节点授权访问
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Value标签设置系统属性
    @Value("${actuator.security.endpoints:#{null}}")
    private String endpoints;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable();

        //开启actuator端点全部都需要验证
        //http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                //.anyRequest().authenticated().and().httpBasic();

        //仅验证名单中的端点需要验证
        http.requestMatcher(EndpointRequest.to(transformEndpoints(endpoints))).authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
    }

    //属性值判断格式
    private String[] transformEndpoints(String endpoints){
        if(isEmpty(endpoints)){
            return new String[0];
        }
        return endpoints.split(",");
    }
}
