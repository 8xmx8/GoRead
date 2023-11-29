package com.yjy.read.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 2343914114441735421L;
    private Long id;

    private String name;

    private String autor;

    private String desc;

    private Integer count;

    private Integer status;

    private String type;


}