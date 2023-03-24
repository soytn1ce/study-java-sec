package com.example.demo.classLoader;

import com.example.demo.uitls.GetClassFileContext;

import java.io.File;

/**
 * 自定义类加载器被加载时，首先执行loadClass方法最后执行findClass
 *Class的getResourceAsStream(String name)方法，参数不以"/"开头则默认从此类对应的.class文件所在的packge下取资源，以"/"开头则从CLASSPATH下获取
 * ClassLoader的getResourceAsStream(String name)方法，默认就是从CLASSPATH下获取资源，参数不可以以"/"开头
 */
public class TestClassLoader extends ClassLoader{
    public TestClassLoader(){}

    public TestClassLoader(ClassLoader parent){super(parent);}

    //打破双亲委派机制，重写loadClass方法，这里直接沿用父类ClassLoader的loadClass即遵循双亲委派机制
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    //不打破双亲委派机制
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("D:/User.class");
        try{
            GetClassFileContext util = new GetClassFileContext();
            byte[] bytes = util.getContext(file);
            Class<?> c = this.defineClass(name, bytes,0,bytes.length);
            return c;
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
