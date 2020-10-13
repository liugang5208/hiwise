package com.sky.hiwise.hbase.domain;

import com.sky.hiwise.hbase.common.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class UserRowMapper implements RowMapper<User> {

    private static byte[] COLUMN_FAMILY = "cf".getBytes();
    private static byte[] ID = "id".getBytes();
    private static byte[] NAME = "name".getBytes();
    private static byte[] EMAIL = "email".getBytes();

    @Override
    public User mapRow(Result result, int rowNum) throws Exception {
        String name = Bytes.toString(result.getValue(COLUMN_FAMILY, NAME));
        String email = Bytes.toString(result.getValue(COLUMN_FAMILY, EMAIL));
        String id = Bytes.toString(result.getValue(COLUMN_FAMILY, ID));
        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setUserName(name);
        user.setEmail(email);
        return user;
    }
}
