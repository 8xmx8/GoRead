package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.common.ReadStatus;
import com.yjy.read.entity.BookRead;
import com.yjy.read.entity.BookShelf;
import com.yjy.read.mapper.BookReadMapper;
import com.yjy.read.mapper.BookShelfMapper;
import com.yjy.read.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookShelfServiceImpl extends ServiceImpl<BookShelfMapper, BookShelf> implements BookShelfService {

    @Autowired
    private BookReadMapper bookReadMapper;
    @Autowired
    private BookShelfMapper bookShelfMapper;
    // 点击阅读
    // 判断是否存在阅读记录   如果不存在  新增
    // 章节为第一章   阅读时间是当前时间   阅读状态是正在读
    // 如果存在    获取阅读记录   找到对应章节   展示其内容
    // 最后阅读章节和最新更新章节的处理

    @Override
    public boolean addToShelf(long userId, long bookId) {
        QueryWrapper<BookRead> wrapper = Wrappers.query();
        wrapper.eq("user_id", userId);
        wrapper.eq("book_id", bookId);
        BookRead bookRead = bookReadMapper.selectOne(wrapper);

        ReadStatus readStatus = ReadStatus.UNREAD;
        if (bookRead != null) {
            if (bookRead.getStatus() == ReadStatus.FINISH.getValue()) {
                readStatus = ReadStatus.FINISH;
            } else {
                readStatus = ReadStatus.READING;
            }
        }

        BookShelf bookShelf = new BookShelf();
        bookShelf.setUserId(userId);
        bookShelf.setBookId(bookId);
        bookShelf.setStatus(readStatus.getValue());
        int insert = bookShelfMapper.insert(bookShelf);
        return insert == 1;
    }

    @Override
    public boolean removeFromShelf(long userId, long bookId) {
        QueryWrapper<BookShelf> wrapper = Wrappers.query();
        wrapper.eq("user_id", userId);
        wrapper.eq("book_id", bookId);
        int delete = bookShelfMapper.delete(wrapper);
        return delete == 1;
    }

}
