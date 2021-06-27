package com.example.restaurant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order implements Serializable {
    private Integer order_id;
    private String user_name;
    private Integer order_prices;

}
