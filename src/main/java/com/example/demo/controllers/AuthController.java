package com.example.demo.controllers;

import com.example.demo.entity.Products;
import com.example.demo.entity.User;
import com.example.demo.services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpClient;

@Controller
public class AuthController {
    @Autowired
    private UserServices userServices;
    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam("isEnabled") boolean isEnabled, @ModelAttribute User user, HttpSession session){
        System.out.println(user);
        User newUser = userServices.saveUser(user , isEnabled);
        if(newUser != null){
            session.setAttribute ("message","User Register successfully");
        }else{
            session.setAttribute("message","Something went wrong");
        }
        return "redirect:/register";

    }
    @GetMapping("/login")
    public String signin(){
        return "signin";
    }

}
