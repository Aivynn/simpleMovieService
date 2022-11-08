package com.project.model;

public enum Permission {
    READ("user:read"),
    WRITE("user:write"),
    ADMIN("admin:write");

    private final String permission;
    Permission(String permission){
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
