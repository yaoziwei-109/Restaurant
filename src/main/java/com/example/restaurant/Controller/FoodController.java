package com.example.restaurant.Controller;

import com.example.restaurant.bean.Food;
import com.example.restaurant.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;

    @PostMapping("/getFoodByFoodId")
    public Food getFoodByFoodId(@RequestBody Map<String, String> map){
        return foodService.getFoodByFoodId(Integer.parseInt(map.get("food_id")));
    }


}
