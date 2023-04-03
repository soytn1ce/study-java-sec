package com.soytn1ce.serdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * curl -v target/vuln --data-binary "poc.ser"
 */

@Controller
public class VulnController {
    @ResponseBody
    @RequestMapping("/vuln")
    public String unser(HttpServletRequest req, HttpServletResponse res)throws Exception{
        InputStream inputStream = req.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.readObject();
        return "cc test";
    }

}
