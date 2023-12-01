package com.yjy.read.redisVo;

import lombok.Data;

@Data
public class RankBean implements Comparable<RankBean> {

    private String key;
    private long bookId;
    private Integer value;

    public RankBean(String key, long bookId, Integer value) {
        this.key = key;
        this.value = value;
        this.bookId = bookId;
    }

    @Override
    public int compareTo(RankBean o) {
        // 从大到小排序  反之从小到大
        return o.value - this.value;
    }
}
