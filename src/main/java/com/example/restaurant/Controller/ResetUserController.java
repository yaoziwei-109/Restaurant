package com.example.restaurant.Controller;

import com.example.restaurant.bean.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ResetUserController {

    @Autowired
    UserService userservice;

    @PostMapping("/user/reset")
    public  String reset(@RequestParam("username") String username,
                         @RequestParam("password")  String password,
                         HttpSession session,
                         Model model)
    {
        if(password.isEmpty()){
            model.addAttribute("msg","修改失败,密码为空");
            return "forgot-password";
        }else {
            if(userservice.getUserByName(username)!=null){
                if(userservice.updateUser(new User(username,password))!=-1){
                    session.setAttribute("msg","修改成功");
                    return "redirect:/login";
                }else {
                    model.addAttribute("msg","修改失败，操作数据库失败");
                    return "forgot-password";
                }
            }else {
                model.addAttribute("msg","修改失败,查无用户");
                return "forgot-password";
            }
        }

    }

    @ResponseBody
    @PostMapping("/user/resetAddressTelephone")
    public  int  reset(@RequestBody Map<String, String> map,
                       HttpSession session)
    {
        String username= (String) session.getAttribute("username");
        User user = new User(username,
                map.get("user_address"),map.get("user_telephone"));
        System.out.println(user);
        return userservice.updateUser(user);

    }


   @ResponseBody
    @GetMapping("/user/getUserByUsername")
    public  User getUser(HttpSession session){
        String username= (String) session.getAttribute("username");
        return  userservice.getUserByName(username);
    }
}
