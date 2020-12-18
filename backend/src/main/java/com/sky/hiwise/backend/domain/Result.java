package com.sky.hiwise.backend.domain;

import com.sky.hiwise.backend.enums.IResult;
import com.sky.hiwise.backend.enums.ResultEnum;
import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(int status, String message) {
        this.code = status;
        this.message = message;
    }

    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> fallback(IResult result) {
        return new Result<>(result.getCode(), result.getMessage());
    }

    public static <T> Result<T> fallback(String errorMsg) {
        return new Result<>(ResultEnum.FALL_BACK.getCode(), errorMsg);
    }

    public static <T> Result<T> fallback() {
        return new Result<>(ResultEnum.FALL_BACK.getCode(), ResultEnum.FALL_BACK.getMessage());
    }

    public static <T> Result<T> notFound(String errorMsg) {
        return new Result<>(ResultEnum.NOT_FOUND.getCode(), errorMsg);
    }

    public static <T> Result<T> notFound() {
        return new Result<>(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getMessage());
    }
}
