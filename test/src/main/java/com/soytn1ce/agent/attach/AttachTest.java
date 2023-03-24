package com.soytn1ce.agent.attach;

public class AttachTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            System.out.println("process result: " + process());
            Thread.sleep(5000);
        }
    }

    public static String process(){
        System.out.println("process!");
        return "success";
    }
}