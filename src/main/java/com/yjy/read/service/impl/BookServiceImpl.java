package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.entity.Book;
import com.yjy.read.mapper.BookMapper;
import com.yjy.read.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
