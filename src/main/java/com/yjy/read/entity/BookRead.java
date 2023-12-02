package com.yjy.read.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BookRead {
    private Long id;

    private Long userId;

    private Long bookId;

    private Long chapterId;

    private Date lastReadTime;

    private Integer status;


}