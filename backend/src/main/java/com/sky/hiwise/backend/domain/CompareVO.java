package com.sky.hiwise.backend.domain;

import lombok.Data;

@Data
public class CompareVO {

    private boolean equals;

    private String oldResult;

    private String newResult;
}
