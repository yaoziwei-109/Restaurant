package com.example.restaurant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class OrderItem implements Serializable {
    private Integer order_item_id;
    private Integer order_id;
    private Integer food_id;
    private Integer order_item_food_number;
    private String user_name;


}
