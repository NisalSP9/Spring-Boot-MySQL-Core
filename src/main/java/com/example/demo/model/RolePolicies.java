package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(RolePoliciesId.class)
public class RolePolicies {

    @Id
    private Long roleId;

    @Id
    private Long policyId;

    public RolePolicies() {
    }

    public RolePolicies(Long roleId, Long policyId) {
        this.roleId = roleId;
        this.policyId = policyId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }
}
