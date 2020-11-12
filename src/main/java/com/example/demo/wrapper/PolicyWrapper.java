package com.example.demo.wrapper;

public class PolicyWrapper {

    private String resource;
    private Integer access;

    public PolicyWrapper() {
    }

    public PolicyWrapper(String resource, Integer access) {
        this.resource = resource;
        this.access = access;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }
}
