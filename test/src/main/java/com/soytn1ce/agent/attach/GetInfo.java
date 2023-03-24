package com.soytn1ce.agent.attach;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class GetInfo {
    //被转化的类
    public static final String TRANSFORM_CLASS = "com.soytn1ce.agent.attach.AttachTest";

    //动态加载，在main方法被调用之前被调用
    public static void agentmain(String args, Instrumentation inst){
        System.out.println("agentmain start");

    }

    private static void addTransformer(Instrumentation inst){
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                try{
                    className = className.replace("/", ".");
                    if(className.equals(TRANSFORM_CLASS)){
                        final ClassPool classPool = ClassPool.getDefault();
                        final CtClass clazz = classPool.get(TRANSFORM_CLASS);

                        for(CtMethod method : clazz.getMethods()){
                            if(Modifier.isNative(method.getModifiers())){
                                continue;
                            }
                            //在开始之前加入语句
                            method.insertBefore("System.out.println(\"" + clazz.getSimpleName() + "." + method.getName() + " start.\");");
                            //在结束之后加入语句
                            method.insertAfter("System.out.println(\"" + clazz.getSimpleName() + "." + method.getName() + " end.\");");
                        }
                        return clazz.toBytecode();
                    }
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                } catch (CannotCompileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
}
