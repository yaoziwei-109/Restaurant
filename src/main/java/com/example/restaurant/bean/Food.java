package com.example.restaurant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Food {
    private Integer food_id;
    private Integer food_price;
    private String food_name;
    private Integer food_number;
    private Integer version;
}
