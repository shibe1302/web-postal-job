package com.shiba1302.timkiemvieclam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shiba1302.timkiemvieclam.services.UserServices;

@Controller
public class JobPostActivityController {
    private final UserServices userServices;

    @Autowired
    public JobPostActivityController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/dashboard/")
    public String SearchJob(Model model) {
        Object currrentUserProfile = userServices.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        model.addAttribute("user", currrentUserProfile);
        return "dashboard";
    }

}
