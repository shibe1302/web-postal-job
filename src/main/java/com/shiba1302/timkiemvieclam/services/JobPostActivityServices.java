package com.shiba1302.timkiemvieclam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiba1302.timkiemvieclam.entity.JobPostActivity;
import com.shiba1302.timkiemvieclam.respository.JobPostActivityRespository;

@Service
public class JobPostActivityServices {
    private final JobPostActivityRespository jobPostActivityRespository;

    @Autowired
    public JobPostActivityServices(JobPostActivityRespository jobPostActivityRespository) {
        this.jobPostActivityRespository = jobPostActivityRespository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRespository.save(jobPostActivity);
    }

}
