package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjy.read.entity.Author;
import com.yjy.read.mapper.AuthorMapper;
import com.yjy.read.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Transactional
    public void insertIfNotExists(Author author) {
        QueryWrapper<Author> queryWrapper = Wrappers.query();
        queryWrapper.eq("name", author.getName());
        Author selectOne = authorMapper.selectOne(queryWrapper);
        if (selectOne == null) {
            authorMapper.insert(author);
        }
    }
}
