package com.soytn1ce.memhorse.listen;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class TestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("执行了listener销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("执行了listener初始化");
    }
}
