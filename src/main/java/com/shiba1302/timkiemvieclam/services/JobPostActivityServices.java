package com.shiba1302.timkiemvieclam.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiba1302.timkiemvieclam.entity.IRecruiterJobs;
import com.shiba1302.timkiemvieclam.entity.JobCompany;
import com.shiba1302.timkiemvieclam.entity.JobLocation;
import com.shiba1302.timkiemvieclam.entity.JobPostActivity;
import com.shiba1302.timkiemvieclam.entity.RecruiterJobsDto;
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

    public List<RecruiterJobsDto> getRecruiterListJobs(int recruiter) {
        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRespository.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), "", rec.getName());
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(),
                    rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDtoList;
    }
}
