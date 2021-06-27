package com.example.restaurant.service;

import com.example.restaurant.bean.Food;
import com.example.restaurant.bean.Order;
import com.example.restaurant.bean.OrderItem;
import com.example.restaurant.mapper.FoodMapper;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.swing.*;
import java.beans.Transient;
import java.util.ArrayList;

@Service
public class FoodService {

    @Autowired
    FoodMapper foodMapper;
    @Autowired
    OrderItemService orderItemService;

    public Food getFoodByFoodId(Integer foodId){
        return foodMapper.getFoodByFoodId(foodId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int  updateNumber(ArrayList<OrderItem> cache, Order order) {
        //try {

            return 0;
            /*
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return  -1;
        }

             */
    }
}
