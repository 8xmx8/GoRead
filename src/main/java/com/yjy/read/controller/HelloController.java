package com.yjy.read.controller;


import com.yjy.read.entity.User;
import com.yjy.read.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private UserService userService;

    @RequestMapping("/mybatisplus")
    @ResponseBody
    public List<User> mybatisplus() {
        List<User> list = userService.list();
        return list;
    }
//    @RequestMapping("/addBook")
    
}
