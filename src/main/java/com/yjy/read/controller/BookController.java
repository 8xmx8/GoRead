package com.yjy.read.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yjy.read.entity.Book;
import com.yjy.read.service.BookService;
import com.yjy.read.service.RankService;
import com.yjy.read.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RankService rankService;


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        System.out.println(keyword);
        QueryWrapper<Book> wrapper = Wrappers.query();
        wrapper.like("name", keyword);
        List<Book> list = bookService.list(wrapper);

        List<BookVo> voList = new ArrayList<>();
        for (Book book : list) {
            BookVo vo = new BookVo();
            vo.setId(book.getId());
            vo.setName(book.getName());
            vo.setAuthor(book.getAuthor());
            vo.setDesc(book.getDescription());
            vo.setImgPath(book.getId() + ".jpg");
            rankService.recordSearchCount(book.getId());
            voList.add(vo);
        }
        model.addAttribute("voList", voList);
        // 展示到列表页中
        return "list";
    }
}
