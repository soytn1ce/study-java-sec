package com.example.demo.testExam;

import com.example.demo.classLoader.TestClassLoader;
import org.jolokia.util.JolokiaCipher;

import java.security.GeneralSecurityException;
import java.sql.DriverManager;

public class ClassLoaderTest {
    public static void main(String[] args) throws GeneralSecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //获取当前类的类加载器
        ClassLoader c = ClassLoaderTest.class.getClassLoader();
        System.out.println(c);
        System.out.println(c.getParent().getResource(""));
        System.out.println(c.getResource(""));

        //获取当前线程的类加载器
        ClassLoader c1 = Thread.currentThread().getContextClassLoader();
        System.out.println(c1);
        System.out.println(c1.getResource(""));

        //获取系统的ClassLoader
        ClassLoader c2 = ClassLoader.getSystemClassLoader();
        System.out.println(c2);

        //获取调用者的ClassLoader
        //native方法，DriverManager.getCallerClassLoader

        TestClassLoader testClassLoader = new TestClassLoader(ClassLoader.getSystemClassLoader().getParent());
        //指定类加载器使用自定义类加载器，Class.forName使用的系统类加载器Application ClassLoader
        Class<?> c3 = Class.forName("com.example.demo.dao.User",true,testClassLoader);
        System.out.println(c3.newInstance());
        System.out.println(c3.newInstance().getClass().getClassLoader());

    }
}
