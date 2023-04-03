package com.soytn1ce.serdemo.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 继承MethodInterceptor，作为动态代理类重写intercept
 */
public class Subject1Interceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Subject1Interceptor:before invokeSuper" );
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("Subject1Interceptor:after invokeSuper");
        return result;
    }
}
