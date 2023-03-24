package com.example.demo.agent;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.builders.StringElementFacetBuilder;

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
        return objectInputStream.readObject().toString();

    }
}
