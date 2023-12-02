package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.entity.BookRead;
import com.yjy.read.mapper.BookReadMapper;
import com.yjy.read.service.BookReadService;
import org.springframework.stereotype.Service;

@Service
public class BookReadServiceImpl extends ServiceImpl<BookReadMapper, BookRead> implements BookReadService {
}
