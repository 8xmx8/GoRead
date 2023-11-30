package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.entity.Chapter;
import com.yjy.read.mapper.ChapterMapper;
import com.yjy.read.service.ChapterService;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
        implements ChapterService {
}
