package com.yjy.read.service;

import com.yjy.read.entity.Book;

import java.util.List;

public interface RankService {
    void recordSearchCount(long bookId);

    List<Book> rankSearchCount();
}
