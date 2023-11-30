package com.yjy.read.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjy.read.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectByName(String name);
}
