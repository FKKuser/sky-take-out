package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "shop related interface")
public class AdminShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * set the status of the shop
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("set the status of the shop")
    public Result setStatus(@PathVariable Integer status){
        log.info("Set the status of the shop to: {}", status == 1 ? "opening" : "closed");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }


    /**
     * get the shop status
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("get the shop status")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("the shop status is: {}", status == 1 ? "opening":"closing");
        return Result.success(status);
    }
}
