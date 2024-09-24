package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * search user according to openid
     * @param openid
     * @return
     */
    @Select("select * from sky_take_out.user where openid = #{openid}")
    User getByOpenid(String openid);


    void insert(User user);

    @Select("select * from sky_take_out.user where id = #{id}")
    User getById(Long userId);


    /**
     * user number according to dynamic sql
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
