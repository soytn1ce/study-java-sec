package com.soytn1ce.serdemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler.invoke，通过原始对象方法实现代理方法的调用
 */
public class DynamicSubject implements InvocationHandler {
    private Object realSubject;
    public DynamicSubject(Object realSubject){this.realSubject=realSubject;}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DynamicSubject:Logging before request");
        Object result = method.invoke(realSubject,args);
        System.out.println("DynamicSubject:Logging after request");
        return  result;
    }
}
