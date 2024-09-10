package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 动态条件查询
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);


    @Update("UPDATE sky_take_out.shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);


    /**
     * insert setmeal or dish into shopping cart
     * @param shoppingCart
     */
    @Insert("insert into sky_take_out.shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) " +
            "values (#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);


    /**
     * delete shopping cart by user ID
     * @param currentId
     */
    @Delete("delete from sky_take_out.shopping_cart where user_id = #{currentId}")
    void deleteByUserId(Long currentId);

    @Delete("delete from sky_take_out.shopping_cart where id = #{id}")
    void deleteById(Long id);
}
