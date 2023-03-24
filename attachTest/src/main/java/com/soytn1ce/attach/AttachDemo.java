package com.soytn1ce.attach;

import java.lang.instrument.Instrumentation;

public class AttachDemo {
    public static void agentmain(String agentArgs, Instrumentation inst) {
        for (int i = 0; i < 10; i++) {
            System.out.println("hello I`m agentMain!!!");
        }
    }
}
