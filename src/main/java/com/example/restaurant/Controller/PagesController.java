package com.example.restaurant.Controller;

import com.example.restaurant.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PagesController {
    @Autowired
    OrderItemService orderItemService;
    @GetMapping(value={"/cards"})
    public  String cards(){

        return "cards";
    }

    @GetMapping(value={"/tables"})
    public  String tables(Model model,HttpSession session){
        model.addAttribute("username",session.getAttribute("username"));
        return "tables";
    }
    @GetMapping(value={"/info"})
    public  String info(Model model,HttpSession session){
        model.addAttribute("username",session.getAttribute("username"));
        return "info";
    }

    @RequestMapping(value={"/manage"})
    public  String manage(Model model,HttpSession session){
        model.addAttribute("username",session.getAttribute("username"));
        return "manage";
    }

    @RequestMapping(value={"/main"})
    public  String main(Model model,HttpSession session){
        String username= (String) session.getAttribute("username");
        model.addAttribute("username",username);
        return "index";
    }

    @GetMapping("/register")
    public  String returnRegister(){
        return "register";
    }



    @GetMapping("/forgotpassword")
    public  String returnForgotPassword(){
        return "forgot-password";
    }
}
