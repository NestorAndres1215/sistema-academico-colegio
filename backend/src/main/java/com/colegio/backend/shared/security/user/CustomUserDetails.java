package com.colegio.backend.shared.security.user;


import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.shared.constant.StatusConstants;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails  implements UserDetails {

    private  final User user;

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return user.getRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority(
                                role.getName().toUpperCase()
                        )
                )
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @NonNull
    public String getUsername() {
        return Optional.ofNullable(user.getUsername())
                .filter(username -> !username.isBlank())
                .orElse(user.getEmail());
    }

    @Override
    public boolean isEnabled() {
        return StatusConstants.ACTIVE.equals(user.getStatus());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return StatusConstants.ACTIVE.equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}