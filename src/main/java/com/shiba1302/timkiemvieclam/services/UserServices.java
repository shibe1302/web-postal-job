package com.shiba1302.timkiemvieclam.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiba1302.timkiemvieclam.entity.JobSeekerProfile;
import com.shiba1302.timkiemvieclam.entity.RecruiterProfile;
import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.respository.JobSeekerProfileRepository;
import com.shiba1302.timkiemvieclam.respository.RecruiterProfileRepository;
import com.shiba1302.timkiemvieclam.respository.UsersRespository;

@Service
public class UserServices {
    private final UsersRespository usersRespository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UserServices(UsersRespository usersRespository, JobSeekerProfileRepository jobSeekerProfileRepository1,
            RecruiterProfileRepository recruiterProfileRepository1) {
        this.usersRespository = usersRespository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository1;
        this.recruiterProfileRepository = recruiterProfileRepository1;

    }

    public Users addnew(Users u) {
        u.setActive(true);
        u.setRegistrationDate(new Date(System.currentTimeMillis()));
        int userTypeID = u.getUserTypeId().getUserTypeId();
        usersRespository.save(u);
        if (userTypeID == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(u));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(u));
        }
        return usersRespository.save(u);
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRespository.findByEmail(email);

    }

}
