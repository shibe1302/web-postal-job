package com.shiba1302.timkiemvieclam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.respository.UsersRespository;
import com.shiba1302.timkiemvieclam.util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRespository usersRepository;

    @Autowired
    public CustomUserDetailsService(UsersRespository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user"));
        return new CustomUserDetails(user);
    }
}
