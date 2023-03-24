package com.example.agentspring;


import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;

@Controller
public class AgentController {

    @ResponseBody
    @RequestMapping("/vuln")
    public String unserVuln(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        InputStream inputStream = request.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.readObject();
        return "vuln ok";
    }

    @ResponseBody
    @RequestMapping("/demo")
    public String demo(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        return "demo";
    }
}