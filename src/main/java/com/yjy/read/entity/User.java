package com.yjy.read.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 6816714543909636905L;
    private Long id;
    private String name;
    private String password;
}
