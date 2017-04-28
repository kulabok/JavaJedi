package ua.com.javajedi.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    ANONIMOUS,
    USER,
    ADMIN;

        @Override
        public String getAuthority() {
            return name();
    }
}
