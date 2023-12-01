package com.yjy.read.controller;


import com.yjy.read.entity.Book;
import com.yjy.read.entity.User;
import com.yjy.read.service.BookService;
import com.yjy.read.service.UserService;
import com.yjy.read.util.JsoupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/test")
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

    @Autowired
    private BookService bookService;
    @Autowired
    private JsoupUtil jsoupUtil;

    @RequestMapping("/addBook")
    @ResponseBody
    public String addBook() {
        Book book = new Book();
        book.setName("夜的命名术");
        book.setAuthor("会说话的肘子");
        book.setDescription("　蓝与紫的霓虹中，浓密的钢铁苍穹下，数据洪流的前端，是科技革命之后的世界，也是现实与虚幻的分界。\n" +
                "　　钢铁与身体，过去与未来。\n" +
                "　　这里，表世界与里世界并存，面前的一切，像是时间之墙近在眼前。\n" +
                "　　黑暗逐渐笼罩。\n" +
                "　　可你要明白啊我的朋友，我们不能用温柔去应对黑暗，要用火。");
        book.setStatus(1);
        book.setCount(2259300);
        book.setType("都市");
        bookService.save(book);
        long id = book.getId();
        System.out.println(id);

        String str = "https://book.qidian.com/info/1021617576";
        jsoupUtil.handler(str);

        return id + "";
    }

    @RequestMapping("/addBookUrl")
    @ResponseBody
    public String addBookUrl() {
        String str = "https://book.qidian.com/info/1021617576";
        jsoupUtil.handler(str);
        return "Success";
    }

}
