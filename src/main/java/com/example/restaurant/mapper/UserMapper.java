package com.example.restaurant.mapper;



import com.example.restaurant.bean.User;
import org.apache.ibatis.annotations.*;



public interface UserMapper {

     User getuser(String username);

     User GetUserByName(String name);

     int addUser(String username,String password);

     int updateUser(String password,String address,String telephone,int id);



}
