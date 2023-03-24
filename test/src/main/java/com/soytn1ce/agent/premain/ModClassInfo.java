package com.soytn1ce.agent.premain;

import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * premain只能修改已经加载到类的属性，不能直接修改未加载的类属性
 */
public class ModClassInfo {
    private static Instrumentation INST;
   //premain方法，拥有Instrumentation参数的优先级更高
    public static void premain(String agentArgs){
        System.out.println("i'm no Instrumentation premain");
    }

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("i'm Instrumentation premain");
        inst.addTransformer(new StringTransformer());
    }

    static class StringTransformer implements java.lang.instrument.ClassFileTransformer {
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                java.security.ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            // 只处理 java.lang.String 类
            System.out.println(className);
            if (!className.equals("com/soytn1ce/agent/dao/User")) {
                return null;
            }

            try {
                System.out.println("i found id");
                javassist.ClassPool cp = javassist.ClassPool.getDefault();
                javassist.CtClass cc = cp.get("com.soytn1ce.agent.dao.User");
                // 查找 toString() 方法并在其中插入代码
                javassist.CtMethod method = cc.getDeclaredMethod("toString");
                method.insertBefore("{ System.out.println(\"insert word! \" ); }");
                // 转换为 byte 数组并返回
                return cc.toBytecode();
            } catch (Throwable e) {
                // 忽略任何异常
                return null;
            }
        }
    }

}
