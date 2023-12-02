package com.yjy.read.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjy.read.entity.BookRead;

public interface BookReadService extends IService<BookRead> {
    String readRecord(long userId, long bookId);
}
