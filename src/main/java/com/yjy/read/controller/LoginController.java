package com.yjy.read.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.read.entity.User;
import com.yjy.read.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        // 接收表单 要进行参数有效性的验证
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            model.addAttribute("msg", "用户名或密码不能为空");
            return "login";
        }

        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("name", username);
        User user = userService.getOne(wrapper);
        if (user == null) {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }

        if (!password.equals(user.getPassword())) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
        session.setAttribute("user", user.getId());
        return "redirect:/index";
    }
}
