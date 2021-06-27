package com.example.restaurant.Controller;

import com.example.restaurant.bean.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    UserService userservice;

    @PostMapping("/user/register")
    public  String register(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("repeatPassword") String repeatPassword,
                            HttpSession session,
                            Model model)
    {
        if(repeatPassword.equals(password)){
            if(userservice.getUserByName(username)==null){
                if(userservice.addUser(new User(username,password))!=-1){
                    session.setAttribute("msg","注册成功");
                    return "redirect:/login";
                }else {
                    model.addAttribute("msg","注册失败,插入用户信息到数据库失败");
                    return "register";
                }
            }else {
                model.addAttribute("msg","注册失败,有重复名字");
                return "register";
            }
        }else {
            model.addAttribute("msg","注册失败，密码没有正确重复输入");
            return "register";
        }

    }




}
