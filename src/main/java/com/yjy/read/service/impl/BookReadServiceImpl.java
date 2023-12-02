package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.common.ReadStatus;
import com.yjy.read.entity.BookRead;
import com.yjy.read.entity.Chapter;
import com.yjy.read.entity.ChapterContent;
import com.yjy.read.mapper.BookReadMapper;
import com.yjy.read.mapper.ChapterContentMapper;
import com.yjy.read.mapper.ChapterMapper;
import com.yjy.read.service.BookReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookReadServiceImpl extends ServiceImpl<BookReadMapper, BookRead> implements BookReadService {
    @Autowired
    private BookReadMapper bookReadMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private ChapterContentMapper contentMapper;

    // 点击阅读
    // 判断是否存在阅读记录   如果不存在  新增
    // 章节为第一章   阅读时间是当前时间   阅读状态是正在读
    // 如果存在    获取阅读记录   找到对应章节   展示其内容
    // 最后阅读章节和最新更新章节的处理
    @Override
    public String readRecord(long userId, long bookId) {
        QueryWrapper wrapper = Wrappers.query();
        wrapper.eq("user_id", userId);
        wrapper.eq("book_id", bookId);
        BookRead bookRead = bookReadMapper.selectOne(wrapper);
        if (bookRead == null) {
            bookRead = new BookRead();
            bookRead.setUserId(userId);
            bookRead.setBookId(bookId);

            wrapper = Wrappers.query();
            wrapper.eq("sort", 1);
            Chapter chapter = chapterMapper.selectOne(wrapper);
            bookRead.setChapterId(chapter.getId());

            bookRead.setLastReadTime(new Date());
            bookRead.setStatus(ReadStatus.READING.getValue());
            bookReadMapper.insert(bookRead);

            ChapterContent content = contentMapper.selectById(chapter.getContentId());
            return content.getContent();
        }

        bookRead.setLastReadTime(new Date());
        UpdateWrapper<BookRead> updateWrapper = Wrappers.update();
        updateWrapper.eq("user_id", userId);
        updateWrapper.eq("book_id", bookId);
        bookReadMapper.update(bookRead, updateWrapper);

        Chapter chapter = chapterMapper.selectById(bookRead.getChapterId());
        ChapterContent content = contentMapper.selectById(chapter.getContentId());
        return content.getContent();
    }
}
