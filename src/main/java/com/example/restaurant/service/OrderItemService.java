package com.example.restaurant.service;

import com.example.restaurant.bean.Food;
import com.example.restaurant.bean.OrderItem;
import com.example.restaurant.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
     @Autowired
    OrderItemMapper orderItemMapper;

    public boolean addOrderItem(OrderItem orderItem){
        if(orderItemMapper.addOrderItem(orderItem.getOrder_id(),
                orderItem.getFood_id(),orderItem.getOrder_item_food_number(),
                 orderItem.getUser_name())!=-1){
             return  true;
        }else {
            return  false;
        }
    }

    public List<OrderItem> getAllCacheOrderItem(String username){
        return orderItemMapper.getAllCacheOrderItem(username);
    }

    public OrderItem getOrderItemCacheByFoodId(Integer foodId,String username){
        return orderItemMapper.getOrderItemCacheByFoodId(foodId,username);
    }

    public int updateOrderItemOrderIdByFoodId(Integer orderId,Integer foodId,String username){
        return orderItemMapper.updateOrderItemOrderIdByFoodId(orderId,foodId,username);
    }

    public int deleteOrderItemByFoodId(Integer foodId,String username){
        if(orderItemMapper.deleteOrderItemByFoodId(foodId,username)!=-1){
            return 1; //删除成功
        }else {
            return -1;
        }
    }


    public int updateOrderItemNumberByFoodId(Integer foodId,Integer foodNumber,String username){
        return orderItemMapper.updateOrderItemNumberByFoodId(foodId,foodNumber,username);
    }

    public List<OrderItem> getItemsByOrderId(Integer orderId){
        return orderItemMapper.getItemsByOrderId(orderId);
    }
}
