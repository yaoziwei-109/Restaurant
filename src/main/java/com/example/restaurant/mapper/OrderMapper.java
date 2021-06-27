package com.example.restaurant.mapper;

import com.example.restaurant.bean.Order;

import java.util.List;

public interface OrderMapper {

    public int createOrder(Integer orderId,String username,Integer orderPrices);

    public List<Order> getAllOrder(String username);

    public Order getAllOrderById(String username,Integer orderId);
}
