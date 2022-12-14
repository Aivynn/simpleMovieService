package com.project.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {
    USER(Set.of(Permission.READ)),
    MANAGER(Set.of(Permission.READ,Permission.WRITE)),
    ADMIN(Set.of(Permission.READ,Permission.WRITE,Permission.ADMIN));



    public Set<Permission> getPermissions() {
        return permissions;
    }

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
