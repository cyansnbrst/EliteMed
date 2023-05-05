package com.cyansnbrst.EliteMed.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
