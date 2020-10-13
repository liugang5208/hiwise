package com.sky.hiwise.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.hiwise.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
