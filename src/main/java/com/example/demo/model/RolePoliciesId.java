package com.example.demo.model;

import java.io.Serializable;

public class RolePoliciesId implements Serializable {

    private Long roleId;

    private Long policyId;

    // default constructor


    public RolePoliciesId() {
    }

    public RolePoliciesId(Long roleId, Long policyId) {
        this.roleId = roleId;
        this.policyId = policyId;
    }

    // equals() and hashCode()

}
