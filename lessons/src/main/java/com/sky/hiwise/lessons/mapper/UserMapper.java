package com.sky.hiwise.lessons.mapper;


import com.sky.hiwise.lessons.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    List<User> findByIds(List<Integer> ids);
}
