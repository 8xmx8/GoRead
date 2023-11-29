package com.yjy.read.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello SpringBoot";
    }

    @RequestMapping("/thyme")
    public String thyme() {
        return "hello";
    }

}
