package com.example.restaurant.Controller;


import com.example.restaurant.bean.Food;
import com.example.restaurant.bean.Order;
import com.example.restaurant.bean.OrderItem;
import com.example.restaurant.service.FoodService;
import com.example.restaurant.service.OrderItemService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    FoodService foodService;

    /**
     * 添加商品item到mysql
     * @param map
     * @param session
     * @return
     */

    @PostMapping("/updateFoodState")
    public int updateFoodState(@RequestBody  Map<String, String> map,
                               HttpSession session){
        //生成OrderItem 此时未生成订单，但标明OrderItem的归属
        int  foodId =Integer.parseInt(map.get("food_id"));
        String username= (String) session.getAttribute("username");
        OrderItem orderItem =new OrderItem(null,null,foodId,1,username);
        orderItemService.addOrderItem(orderItem);
        return 0;
    }

    @PostMapping("/getFoodState")
    public int getFoodState(@RequestBody  Map<String, String> map,
                            HttpSession session){
        //生成OrderItem 此时未生成订单，但标明OrderItem的归属
        int  foodId =Integer.parseInt(map.get("food_id"));
        String username= (String) session.getAttribute("username");
        OrderItem oldOrderItem = orderItemService.getOrderItemCacheByFoodId(foodId,username);
        if(oldOrderItem==null){    //判断oldOrderItem是否存在
            return 0;         //不存在返回0
        }else {
            return  -1;
        }
    }


    @GetMapping("/getAllCacheOrderItem")
    public List<OrderItem> getAllCacheOrderItem(HttpSession session){
        String username= (String) session.getAttribute("username");
        //使用Redis缓存未购买商品界面
        //List<OrderItem> cache = redisTemplate.opsForHash().values("cache");
        //return  cache;
         return orderItemService.getAllCacheOrderItem(username);

    }


    @PostMapping("/deleteOrderItemByFoodId")
    public int deleteOrderItemByFoodId(@RequestBody  Map<String, String> map,
                                  HttpSession session){
        int  foodId =Integer.parseInt(map.get("food_id"));
        String username= (String) session.getAttribute("username");
        return  orderItemService.deleteOrderItemByFoodId(foodId,username);
    }

    /**
     * 创建订单接口 订单ID取系统时间 价格由前端上传
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/createOrder")
    public int createOrder(@RequestBody  Map<String, String> map,
                           HttpSession session){
        long id=System.currentTimeMillis();
        int  prices =Integer.parseInt(map.get("food_prices"));
        String username= (String) session.getAttribute("username");
        ArrayList<OrderItem> cache =(ArrayList<OrderItem>) orderItemService.getAllCacheOrderItem(username);

        if (cache.size() != 0){
            int i=orderService.createOrder(new Order((int) id, username, prices), cache);
            Order order = orderService.getOrderById(username,Integer.valueOf((int)id));
            System.out.println(order);
            if(order!=null){
                redisTemplate.opsForHash().put(username,Long.valueOf(id).toString(),order);
            }
            return i;
        }
        else
            return -1;
    }


    @PostMapping("/updateOrderItemNumber")
    public int updateOrderItemNumber(@RequestBody  Map<String, String> map,
                           HttpSession session){
        int number =Integer.parseInt(map.get("food_number"));
        int foodId =Integer.parseInt(map.get("food_id"));
        String username= (String) session.getAttribute("username");
        return orderItemService.updateOrderItemNumberByFoodId(foodId,number,username);
    }

    /**
     * 返回用户所有订单
     * @param session
     * @return
     */
    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder( HttpSession session){
        String username= (String) session.getAttribute("username");
        return redisTemplate.opsForHash().values(username);
    }

    @PostMapping("/getItemsByOrderId")
    public List<OrderItem> getItemsByOrderId(@RequestBody  Map<String, String> map){
        System.out.println(orderItemService.getItemsByOrderId(Integer.parseInt(map.get("order_id"))));
           return orderItemService.getItemsByOrderId(Integer.parseInt(map.get("order_id")));
    }


    @PostConstruct
    public void init(){
        RedisSerializer redisSerializer =redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
    }
}


