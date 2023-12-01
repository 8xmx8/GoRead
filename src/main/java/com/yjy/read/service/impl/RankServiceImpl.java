package com.yjy.read.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjy.read.common.Commons;
import com.yjy.read.entity.Book;
import com.yjy.read.mapper.BookMapper;
import com.yjy.read.redisVo.RankBean;
import com.yjy.read.service.RankService;
import com.yjy.read.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void recordSearchCount(long bookId) {
        String key = bookId + Commons.SEARCH_NUM;
        // 拼装好key 判断是否存在
        // 如果存在  自增value值  如果不存在  赋值为1
        if (redisUtil.hasKey(key)) {
            redisUtil.incr(key);
        } else {
            redisUtil.set(key, 1);
        }
    }

    @Override
    public List<Book> rankSearchCount() {
        List<Book> result = new ArrayList<>();
        // 遍历出所有包含 _search_num的key值
        // 排序 返回前10
        String pattern = "*" + Commons.SEARCH_NUM;
        Set<String> keys = redisUtil.getKeys(pattern);
        // 根据key提取value
        List<RankBean> list = new ArrayList<>();
        for (String key : keys) {
            long bookId = Long.parseLong(key.substring(0, key.length() - 11));
            int value = (int) redisUtil.get(key);
            RankBean rankBean = new RankBean(key, bookId, value);
            list.add(rankBean);
        }
        Collections.sort(list);

        // 取其中前十位
        int cnt = 0;
        for (RankBean rankBean : list) {
            cnt++;
            if (cnt > 10) break;
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id", rankBean.getBookId());
            Book book = bookMapper.selectOne(wrapper);
            result.add(book);
        }
        return result;
    }

}
