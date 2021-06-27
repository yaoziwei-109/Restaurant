package com.example.restaurant.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_address;
    private String user_telephone;

    public User(String name, String password) {
        this.user_name = name;
        this.user_password = password;
    }
   public  User(String user_name,String user_address,String user_telephone){
        this.user_name=user_name;
        this.user_address=user_address;
        this.user_telephone= user_telephone;
   }
}
