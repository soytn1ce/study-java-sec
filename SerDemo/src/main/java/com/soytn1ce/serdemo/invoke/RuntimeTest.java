package com.soytn1ce.serdemo.invoke;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 静态方法static不需要实例化，所以可以通过getRuntime直接获取Runtime
 * Method-> Class.getMethod(methodname)
 * Method.invoke(Instance, args)
 */
public class RuntimeTest {
    public static void main(String[] args)throws Exception {
        Class clazz = Class.forName("java.lang.Runtime");
        //获取类的所有构造方法
        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println(constructors.length);
        Constructor constructor = constructors[0];
        //通过反射设置私有属性可访问
        constructor.setAccessible(true);
        Runtime rt=(Runtime) constructor.newInstance();
        //rt.exec("calc");


        Class clazz1 = Class.forName("java.lang.Runtime");
        //通过getRuntime返回当前的Runtime实例
        Method method = clazz.getMethod("getRuntime");
        Method exec = clazz1.getMethod("exec", String.class);
        //通过getRuntime获取static Runtime的实例 --> invoke(clazz1)
        exec.invoke(method.invoke(method.invoke(clazz1)),"calc");
        //clazz1.getMethod("exec",String.class).invoke(method.invoke(clazz1),"calc");


        Class clazz2 = Class.forName("java.lang.Runtime");
        Constructor[]constructors1 = clazz2.getDeclaredConstructors();
        Constructor constructor1 = constructors1[0];
        constructor1.setAccessible(true);
        clazz2.getMethod("exec", String.class).invoke(constructor1.newInstance(),"calc");
    }
}
