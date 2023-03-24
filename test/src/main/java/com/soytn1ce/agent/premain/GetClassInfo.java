package com.soytn1ce.agent.premain;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.util.Arrays;

//获取类信息
public class GetClassInfo {
    public static void premain(String args, Instrumentation inst){
        Class[] classes = inst.getAllLoadedClasses(); //获取所有已加载的类
        for(Class c : classes){
            System.out.println(c.getName()); //获取类名
            //System.out.println(c.getSuperclass());   //获取父类或继承的类
           // System.out.println(Arrays.toString(c.getInterfaces()));   //输出类实现的接口
           // System.out.println(Modifier.toString(c.getModifiers()));   //输出类的修饰符
        }
    }

}
