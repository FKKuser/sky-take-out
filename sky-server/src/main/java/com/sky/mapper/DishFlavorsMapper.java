package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorsMapper {

    /**
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);


    /**
     * @param dishId
     */
    @Delete("delete from sky_take_out.dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);


    /**
     * enquiry flavors according to dishId
     * @param dishId
     * @return
     */
    @Select("SELECT * from sky_take_out.dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
