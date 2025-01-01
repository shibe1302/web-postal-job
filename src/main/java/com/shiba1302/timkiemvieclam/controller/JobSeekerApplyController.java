package com.shiba1302.timkiemvieclam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shiba1302.timkiemvieclam.entity.JobPostActivity;
import com.shiba1302.timkiemvieclam.services.JobPostActivityServices;
import com.shiba1302.timkiemvieclam.services.UserServices;

@Controller
public class JobSeekerApplyController {
    private final JobPostActivityServices jobPostActivityService;
    private final UserServices usersService;

    @Autowired
    public JobSeekerApplyController(JobPostActivityServices jobPostActivityService, UserServices usersService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
    }

    @GetMapping("job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);
        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "job-detail";
    }
}
