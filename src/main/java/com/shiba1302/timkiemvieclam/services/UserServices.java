package com.shiba1302.timkiemvieclam.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.respository.UsersRespository;

@Service
public class UserServices {
    private final UsersRespository usersRespository;

    @Autowired
    public UserServices(UsersRespository usersRespository) {
        this.usersRespository = usersRespository;
    }

    public Users addnew(Users u) {
        u.setActive(true);
        u.setRegistrationDate(new Date(System.currentTimeMillis()));
        return usersRespository.save(u);
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRespository.findByEmail(email);

    }

}
