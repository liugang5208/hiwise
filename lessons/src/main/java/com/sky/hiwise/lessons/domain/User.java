package com.sky.hiwise.lessons.domain;

import lombok.Data;

@Data
public class User {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

    private String description;

    private String headImg;

}
