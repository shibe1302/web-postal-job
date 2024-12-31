package com.shiba1302.timkiemvieclam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.entity.UsersType;
import com.shiba1302.timkiemvieclam.services.UserServices;
import com.shiba1302.timkiemvieclam.services.UsersTypeServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

            return "register";
        }
        userServices.addnew(users);

        return "redirect:/dashboard-main";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

}
