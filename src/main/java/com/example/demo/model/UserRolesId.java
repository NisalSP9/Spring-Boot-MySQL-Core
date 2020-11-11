package com.example.demo.model;

import java.io.Serializable;

public class UserRolesId implements Serializable {

    private Long userId;

    private Long roleId;

    // default constructor


    public UserRolesId() {
    }

    public UserRolesId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    // equals() and hashCode()

}
