package com.shiba1302.timkiemvieclam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiba1302.timkiemvieclam.entity.UsersType;
import com.shiba1302.timkiemvieclam.respository.UsersTypeRespository;

@Service
public class UsersTypeServices {

    private final UsersTypeRespository usersTypeRespository;

    @Autowired
    public UsersTypeServices(UsersTypeRespository usersTypeRespository) {
        this.usersTypeRespository = usersTypeRespository;
    }

    public List<UsersType> getall() {
        return usersTypeRespository.findAll();
    }

}
