package com.soytn1ce.serdemo.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 运行时创建代理类，方法调用时将代理对象转发给内部对象
 * 无需编写大量代理类
 */


public class DynamicProxy {
    public static void main(String[] args) {
        //interDynamic();
        classDynamic();
    }


    /**
     * 基于类的动态代理
     *
     * Subject1Interceptor:before invokeSuper
     * Subject1:Handling request
     * Subject1Interceptor:after invokeSuper
     *
     * 基于类的动态代理无需代理对象实现接口，但需要创建代理类
     * 使用cglib库
     */
    public static void classDynamic(){
        Subject1Interceptor interceptor = new Subject1Interceptor();
        Enhancer enhancer = new Enhancer();
        //使用Enhancer创建所需类的子类，使代理类继承自被代理类
        enhancer.setSuperclass(Subject1.class);
        //将被代理类设置为回调
        enhancer.setCallback(interceptor);
        //创建代理对象
        Subject1 subject1 = (Subject1) enhancer.create();
        subject1.request();
    }


    /**
     * 基于接口的动态代理
     *
     * DynamicSubject:Logging before request
     * RealSubject:handing request
     * DynamicSubject:Logging after request
     *
     * InvocationHandler.invoke，通过原始对象方法实现代理方法的调用
     */
    public static void interDynamic(){
        RealSubject realSubject = new RealSubject();
        InvocationHandler handler = new DynamicSubject(realSubject);
        Subject proxy = (Subject) Proxy.newProxyInstance(
                realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),
                handler);
        proxy.request();
    }
}
