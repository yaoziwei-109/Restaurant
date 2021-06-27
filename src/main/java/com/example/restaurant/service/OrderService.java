package com.example.restaurant.service;

import com.example.restaurant.bean.Order;
import com.example.restaurant.bean.OrderItem;
import com.example.restaurant.mapper.FoodMapper;
import com.example.restaurant.mapper.OrderItemMapper;
import com.example.restaurant.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    FoodMapper foodMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int createOrder(Order order, ArrayList<OrderItem> cache){
        try {
            for (OrderItem oi : cache) {
                int version = foodMapper.getFoodByFoodId(oi.getFood_id()).getVersion();
                int count = foodMapper.getFoodByFoodId(oi.getFood_id()).getFood_number();
                if(count>10){
                    if (foodMapper.updateNumber(oi.getFood_id(), oi.getOrder_item_food_number(), version) != 1) {
                        throw new RuntimeException();
                    }
                    //orderItemMapper.updateOrderItemOrderIdByFoodId((int)order.getOrder_id(),oi.getFood_id(),order.getUser_name());
                }else {
                    throw new RuntimeException();
                }
            }
            orderMapper.createOrder(order.getOrder_id(),order.getUser_name(),order.getOrder_prices());
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return  -1;
        }
    }

    public List<Order> getAllOrder(String username){
        return orderMapper.getAllOrder(username);
    }

    public Order getOrderById(String username,Integer orderId) {
        return  orderMapper.getAllOrderById(username,orderId);
    }
}
