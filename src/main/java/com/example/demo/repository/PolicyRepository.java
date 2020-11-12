package com.example.demo.repository;

import com.example.demo.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy,Long> {

    final String GET_USER_POLICIES = "Select policy from policy where id in \n" +
            "(Select policy_id from role_policies where role_id in \n" +
            "(select user_roles.role_id from user_roles where user_id = :userID))";

    @Query(value = GET_USER_POLICIES, nativeQuery = true)
    public List<String> getUserPolicies(@Param("userID") Long userID);

}
