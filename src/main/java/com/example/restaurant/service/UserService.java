package com.example.restaurant.service;


import com.example.restaurant.bean.User;
import com.example.restaurant.mapper.UserMapper;
import com.example.restaurant.tool.tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserMapper usermapper;


    public User getuserByusername(String username){
        return usermapper.getuser(username);
    }

    public User getUserByName(String name){
        return  usermapper.GetUserByName(name);
    }

    public int addUser(User user) {
        try {
            return usermapper.addUser(user.getUser_name(), user.getUser_password());
        } catch (Exception e) {
            return -1;
        }
    }
    
    public int updateUser(User user){
        try {
            User user1 = getUserByName(user.getUser_name());
            if(user.getUser_password()!=null && !user.getUser_password().isEmpty()){
                user1.setUser_password(user.getUser_password());
            }
            if(user.getUser_address()!=null && !user.getUser_address().isEmpty()){
                user1.setUser_address(user.getUser_address());
            }
            if(user.getUser_telephone()!=null && !user.getUser_telephone().isEmpty()){
               user1.setUser_telephone(user.getUser_telephone());
            }
            return usermapper.updateUser(user1.getUser_password(),user1.getUser_address(),
                                       user1.getUser_telephone(),user1.getUser_id());
        }catch (Exception e){
            return -1;
        }
    }

}
