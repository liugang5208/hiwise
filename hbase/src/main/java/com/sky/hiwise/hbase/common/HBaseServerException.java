package com.sky.hiwise.hbase.common;


/**
 * HBaseServer模块异常.
 */
public class HBaseServerException extends HBaseException {

  private int code;
  private String message;

  public HBaseServerException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
  }

  public HBaseServerException(int code, String message) {
    super(message, null);
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public int errorCode() {
    return this.code;
  }
}
