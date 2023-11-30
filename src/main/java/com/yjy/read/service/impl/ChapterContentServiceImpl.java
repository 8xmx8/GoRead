package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.entity.ChapterContent;
import com.yjy.read.mapper.ChapterContentMapper;
import com.yjy.read.service.ChapterContentService;
import org.springframework.stereotype.Service;

@Service
public class ChapterContentServiceImpl extends ServiceImpl<ChapterContentMapper, ChapterContent>
        implements ChapterContentService {
}
