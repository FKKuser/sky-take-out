package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        // if shopping cart item exist, amount ++
        if (list != null && list.size() > 0){
            ShoppingCart shoppingCartItem = list.get(0);
            shoppingCartItem.setNumber(shoppingCartItem.getNumber() + 1);
            shoppingCartMapper.updateNumberById(shoppingCartItem);
        // if it doesn't exist, add a new item to shopping cart
        } else {
            // 判断添加到购物车的是dish还是setmeal
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null){
                // dish
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());


            } else {
                // set meal
                Long setmealId = shoppingCartDTO.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());

            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }



    }

    @Override
    public List<ShoppingCart> showShoppingCart() {
        Long currentId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(currentId)
                .build();

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        return list;
    }

    @Override
    public void cleanShoppingCart() {
        Long currentId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(currentId);
    }

    /**
     * subtract one item from the shopping cart
     * @param shoppingCartDTO
     */
    @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        // create shopping cart instance
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);

        shoppingCart.setUserId(BaseContext.getCurrentId());

        // use shopping cart instance to query the database
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        if (list != null && list.size() > 0){
            ShoppingCart cartItem = list.get(0);
            Integer cartNumber = cartItem.getNumber();

            if (cartNumber == 1){
                // if cart has one item, delete it
                shoppingCartMapper.deleteById(cartItem.getId());

            } else {
                // if there are more than one item, decrease the number by one and put it back
                cartItem.setNumber(cartNumber - 1);
                shoppingCartMapper.updateNumberById(cartItem);
            }
        }
    }
}
