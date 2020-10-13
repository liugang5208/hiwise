package com.sky.hiwise.hbase.common;

/**
 * base exception,all exception should extend it.
 */
public abstract class HBaseException extends RuntimeException {

  protected String errorMessage;

  public HBaseException(String message, Throwable cause) {
    super(cause);
    this.errorMessage = message;
  }

  public abstract int errorCode();

  public String errorMessage() {
    return this.errorMessage;
  }
}
