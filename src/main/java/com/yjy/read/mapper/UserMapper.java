package com.yjy.read.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjy.read.entity.User;
import com.yjy.read.entity.vo.GroupBean;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<GroupBean> selectGroup();

    List<User> selectByName(String name);
}
