package com.shiba1302.timkiemvieclam.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UsersRespository usersRespository, JobSeekerProfileRepository jobSeekerProfileRepository1,
            RecruiterProfileRepository recruiterProfileRepository1, PasswordEncoder passwordEncoder1) {
        this.usersRespository = usersRespository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository1;
        this.recruiterProfileRepository = recruiterProfileRepository1;
        this.passwordEncoder = passwordEncoder1;

    }

    public Users addnew(Users u) {
        u.setActive(true);
        u.setRegistrationDate(new Date(System.currentTimeMillis()));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
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

    public Object getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String name = authentication.getName();
            Users usr = usersRespository.findByEmail(name)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found " + name + " user"));

            int userID = usr.getUserId();
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userID)
                        .orElse(new RecruiterProfile());
                return recruiterProfile;
            } else {
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userID)
                        .orElse(new JobSeekerProfile());
                return jobSeekerProfile;
            }

        }
        return null;

    }

    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();
            Users users = usersRespository.findByEmail(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("deo thay !"));
            return users;

        }
        return null;
    }

}
