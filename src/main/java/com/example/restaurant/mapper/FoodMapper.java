package com.example.restaurant.mapper;

import com.example.restaurant.bean.Food;

public interface FoodMapper {

    public Food getFoodByFoodId(Integer foodId);
    public int updateNumber(Integer foodId,Integer number,Integer version);
}
