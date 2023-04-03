package com.soytn1ce.serdemo.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class FiltUtils {
    public static String readFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // 输出文件内容至控制台
        String fileContent = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(fileContent);

        bufferedReader.close();
        fileReader.close();
        return fileContent;
    }
}
