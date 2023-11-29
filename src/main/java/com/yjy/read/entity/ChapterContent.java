package com.yjy.read.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChapterContent implements Serializable {
    private static final long serialVersionUID = 5987407309338776682L;
    private Long id;

    private String content;

}