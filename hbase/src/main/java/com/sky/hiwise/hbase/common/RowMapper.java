package com.sky.hiwise.hbase.common;

import org.apache.hadoop.hbase.client.Result;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(Result result, int rowNum) throws Exception;
}
