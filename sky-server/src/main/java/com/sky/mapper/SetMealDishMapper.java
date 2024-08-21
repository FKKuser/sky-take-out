package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    // search set meal ids by dish ids

    List<Long> getSetMealDishByDishIds(List<Long> dishIds);

}
