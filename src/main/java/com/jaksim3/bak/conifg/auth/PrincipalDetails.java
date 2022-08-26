package com.jaksim3.bak.conifg.auth;

import com.jaksim3.bak.domain.member.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Member member;

    public PrincipalDetails(Member member) {
        super();
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return "{noop}" + member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public String getEmail() {
        return member.getEmail();
    }

    public int getAge() {
        return member.getAge();
    }

    public String getJob() {
        return member.getJob();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
