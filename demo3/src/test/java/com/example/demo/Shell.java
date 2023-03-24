package com.example.demo;

import java.io.File;
import java.io.IOException;

public class Shell {
    public Shell(String cmd) throws IOException {
        System.out.println(System.getProperty("java.home").replace("jre","lib") + java.io.File.separator + "tools.jar");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.home").replace("jre","lib") + java.io.File.separator + "tools.jar");
        java.lang.String path = "D:/JAVA/java-project/attachTest/target/attachTest-1.0-SNAPSHOT-jar-with-dependencies.jar";
        File file = new File(path);
        System.out.println(file.exists());
    }
}
