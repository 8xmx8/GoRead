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

    // 当一本书被加入书架时  查询阅读记录   验证其状态    不存在即为未读
    // 存在查看是否已读完 获取阅读时间   根据阅读时间排序
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
