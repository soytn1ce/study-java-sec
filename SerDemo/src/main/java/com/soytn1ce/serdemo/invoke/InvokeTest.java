package com.soytn1ce.serdemo.invoke;

import com.soytn1ce.serdemo.dao.People;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * .invoke
 * invoke的第一个传参是要调用方法所属的实例，第二个传参数传递给方法的实参
 *
 * 通过反射可以获取私有内部类，通过$进行分隔跟私有内部类
 * .newInstance的第一个参数区别于public，第一个参数是类的实例化对象；如果是public，蚕食是构造方法的参数
 */
public class InvokeTest {
    public static void main(String[] args)throws Exception {
        Class<?> clazz = Class.forName("java.util.ArrayList");
        //通过newInstance创建一个ArrayList对象
        Object obj = clazz.newInstance();
        //将add方法存到method对象里
        Method addMethod = clazz.getMethod("add", Object.class);

        //使用method.invoke调用add方法，并传参
        addMethod.invoke(obj,"hello");
        addMethod.invoke(obj,"lalala");
        System.out.println(obj.toString());

        //getPrivate();
        getPriFiled();
    }


    //获取私有内部类
    public static void getPrivate()throws Exception{
        Class clazz = Class.forName("com.soytn1ce.serdemo.dao.People$Nopublic");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        constructor.setAccessible(true);
        constructor.newInstance(new People(),"get private");
    }

    //修改类的私有属性值
    public static void getPriFiled()throws Exception{
        Class clazz = Class.forName("com.soytn1ce.serdemo.dao.People");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        constructor.setAccessible(true);
        People people = (People) constructor.newInstance();
        //获取属性name
        Field field = clazz.getDeclaredField("name");
        //修改私有属性可访问
        field.setAccessible(true);
        field.set(people, "change name~");
        people.getName();
    }
}
