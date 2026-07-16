package com.roottronics.travelcore.security.userdetails;

import com.roottronics.travelcore.user.entity.AppUser;
import com.roottronics.travelcore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        AppUser appUser = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Invalid email or password"));

        return new User(
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getEnabled(),
                appUser.getAccountNonExpired(),
                appUser.getCredentialsNonExpired(),
                appUser.getAccountNonLocked(),
                List.of(new SimpleGrantedAuthority(appUser.getUserType().name()))
        );

    }

}