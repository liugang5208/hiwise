package com.sky.hiwise.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum implements IResult {
    SUCCESS(0, "Success"),
    NOT_FOUND(404, "Not found"),
    FALL_BACK(5000, "Fall back"),
    FAIL(3000, "Fall back");
    int code;
    String message;
}
