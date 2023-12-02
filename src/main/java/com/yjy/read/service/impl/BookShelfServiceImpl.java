package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.entity.BookShelf;
import com.yjy.read.mapper.BookShelfMapper;
import com.yjy.read.service.BookShelfService;
import org.springframework.stereotype.Service;

@Service
public class BookShelfServiceImpl extends ServiceImpl<BookShelfMapper, BookShelf> implements BookShelfService {
}
