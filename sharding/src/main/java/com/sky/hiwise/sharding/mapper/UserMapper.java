package com.sky.hiwise.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.hiwise.sharding.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
