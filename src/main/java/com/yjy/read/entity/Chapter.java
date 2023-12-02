package com.yjy.read.entity;

import lombok.Data;

@Data
public class Chapter {
    private Long id;

    private Long bookId;

    private String name;

    private Long contentId;

    private Long type;

    private Integer sort;
}