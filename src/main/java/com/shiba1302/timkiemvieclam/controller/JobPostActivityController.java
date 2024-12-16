package com.shiba1302.timkiemvieclam.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shiba1302.timkiemvieclam.entity.JobPostActivity;
import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.services.JobPostActivityServices;
import com.shiba1302.timkiemvieclam.services.UserServices;

@Controller
public class JobPostActivityController {
    private final UserServices userServices;
    private final JobPostActivityServices jobPostActivityServices;

    @Autowired
    public JobPostActivityController(UserServices userServices, JobPostActivityServices jobPostActivityServices) {
        this.userServices = userServices;
        this.jobPostActivityServices = jobPostActivityServices;
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
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
            return "dashboard-main";
        }
        return "trang-nguoi-dung";

    }

    @GetMapping("/dashboard/addNew")
    public String addNew(Model model) {
        model.addAttribute("JobPostActivity", new JobPostActivity());
        model.addAttribute("user", userServices.getCurrentUserProfile());
        return "add-jobs";
    }

    @PostMapping("/dashboard/postJob")
    public String addNewJob(JobPostActivity jobPostActivity, Model model) {
        Users user = userServices.getCurrentUser();
        if (user != null) {
            jobPostActivity.setPostedById(user);
        }
        jobPostActivity.setPostedDate(new Date());
        model.addAttribute("jobPostActivity", jobPostActivity);
        JobPostActivity saved = jobPostActivityServices.addNew(jobPostActivity);
        return "redirect:/dashboard/";
    }

}
