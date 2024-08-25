package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api("dishes related API")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("add new dish")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("add new dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("Dish pagination search")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("Dish pagination search: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("delete dishes by batch")
    public Result delete(@RequestParam List<Long> ids){
        log.info("delete dishes: {}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("search dish by dish id")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("search dish by dish id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);

        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("Update the dish")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("Update the dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }
}
