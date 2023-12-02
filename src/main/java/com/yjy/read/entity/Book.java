package com.yjy.read.entity;

import lombok.Data;

@Data
public class Book {
    private Long id;

    private String name;

    private Long authorId;

    private String author;

    private String description;

    private Integer count;

    private Integer status;

    private String type;

    private Integer chapterNum;

}