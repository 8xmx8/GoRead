package com.yjy.read.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Chapter implements Serializable {
    private static final long serialVersionUID = -7661747713895364052L;
    private Long id;

    private Long bookId;

    private String name;

    private Long contentId;

    private Long type;

}