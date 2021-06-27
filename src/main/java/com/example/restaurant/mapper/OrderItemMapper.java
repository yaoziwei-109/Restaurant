package com.example.restaurant.mapper;

import com.example.restaurant.bean.Food;
import com.example.restaurant.bean.OrderItem;

import java.util.List;

public interface OrderItemMapper {

      public int addOrderItem(Integer orderId,Integer foodId,Integer foodNumber,String username);

      public List<OrderItem> getAllCacheOrderItem(String username);

      public OrderItem getOrderItemCacheByFoodId(Integer foodId,String username);

      public int updateOrderItemOrderIdByFoodId(Integer orderId,Integer foodId,String username);

      public int deleteOrderItemByFoodId(Integer foodId,String username);

      public int updateOrderItemNumberByFoodId(Integer foodId,Integer foodNumber,String username);

      public List<OrderItem> getItemsByOrderId(Integer orderId);


}
