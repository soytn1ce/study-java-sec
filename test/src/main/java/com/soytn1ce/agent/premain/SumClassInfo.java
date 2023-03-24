package com.soytn1ce.agent.premain;

import java.lang.instrument.Instrumentation;

//统计类信息
public class SumClassInfo {
    public static void premain(String args, Instrumentation inst){
        Class[] classes = inst.getAllLoadedClasses();
        for(Class c : classes){
            long size = inst.getObjectSize(c);
            System.out.println("Class " + c.getName() + " size: " + size);
        }
    }

}
