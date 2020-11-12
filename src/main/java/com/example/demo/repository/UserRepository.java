package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    final String GET_USER_BY_USERNAME = "Select * from user where username = :username";
    @Query(value = GET_USER_BY_USERNAME, nativeQuery = true)
    public Optional<User> getUserByUsername(@Param("username") String username);



}
