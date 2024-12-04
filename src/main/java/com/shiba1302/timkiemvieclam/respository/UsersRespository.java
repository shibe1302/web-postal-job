package com.shiba1302.timkiemvieclam.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiba1302.timkiemvieclam.entity.Users;

public interface UsersRespository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
