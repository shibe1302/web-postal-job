package com.shiba1302.timkiemvieclam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.entity.UsersType;
import com.shiba1302.timkiemvieclam.entity.demotest;
import com.shiba1302.timkiemvieclam.services.UserServices;
import com.shiba1302.timkiemvieclam.services.UsersTypeServices;

import jakarta.validation.Valid;

@Controller
public class UsersController {

    public final UsersTypeServices usersTypeServices;

    private final UserServices userServices;

    @Autowired
    public UsersController(UsersTypeServices usersTypeServices, UserServices userServices) {
        this.usersTypeServices = usersTypeServices;
        this.userServices = userServices;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> users_type = usersTypeServices.getall();
        System.out.println(users_type);
        model.addAttribute("getAllTypes", users_type);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String addNew(@Valid Users users, Model model) {
        Optional<Users> usOptional = userServices.getUserByEmail(users.getEmail());
        if (usOptional.isPresent()) {
            System.out.println("thay mail");
            model.addAttribute("error", "Email nay da ton tai !");
            List<UsersType> users_type = usersTypeServices.getall();
            model.addAttribute("getAllTypes", users_type);
            model.addAttribute("user", new Users());
            model.addAttribute("cac", "lonnnnnnnn");
            return "register";
        }
        userServices.addnew(users);

        return "dashboard";

    }

    @GetMapping("/demo")
    public String demoValue(Model model, demotest demotest1) {

        if (demotest1 != null) {
            model.addAttribute("demo1", new demotest(demotest1.getHo(), demotest1.getTen()));
        } else {
            model.addAttribute("demo1", new demotest("", ""));
        }

        return "thymeleaf-demo";
    }

    @PostMapping("/demo")
    public String demoValue1(@Valid demotest demotest1, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        System.out.println(demotest1);
        redirectAttributes.addFlashAttribute(demotest1);
        return "redirect:/demo";
    }

}
