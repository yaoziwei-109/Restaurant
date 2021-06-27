package com.example.restaurant.Controller;

import com.example.restaurant.bean.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userservice;

    @PostMapping("/user/login")
    public String submit(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpSession session
    ){
        if(userservice.getUserByName(username)!=null
                && userservice.getUserByName(username).getUser_password().equals(password)){
            session.setAttribute("username",username);
            session.setAttribute("cusername",username);
            session.setAttribute("cpassword",password);
            return "redirect:/main";
        }
        else {
            session.setAttribute("msg","账号或密码错误");
            return "redirect:/login";
        }
    }

    @GetMapping(value={"/"})
    public  String login(HttpSession session){
        if(session.getAttribute("username")!=null){
            return "redirect:/main";
        }else {
            //第一次访问时 清理缓存
            session.removeAttribute("username");
            return "login";
        }
    }

    @GetMapping(value={"/login"})
    public  String login2(Model model, HttpServletRequest request, HttpSession session){
        session.removeAttribute("username");
        if(session.getAttribute("msg")!=null){
            model.addAttribute("msg",request.getSession().getAttribute("msg"));
            session.removeAttribute("msg");
        }
        return "login";
    }

     @GetMapping("/user/getUser")
     @ResponseBody
    public User getuser( HttpSession session){
        String username= (String) session.getAttribute("cusername");
        String password= (String) session.getAttribute("cpassword");
        User u = new User(username,password);
         System.out.println("进入"+u);
        return u ;
     }
}
