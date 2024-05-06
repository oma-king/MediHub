package com.kingoma.config;

import com.kingoma.entities.User;
import com.kingoma.enums.AccountType;
import com.kingoma.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No user found with username");
        }

        // Fetch user roles from database (assuming roles are stored as strings)
        Set<AccountType> roleNames = Collections.singleton(user.getAccountType()); // Replace this with your actual method to fetch roles

        // Map user roles to Spring Security authorities
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (AccountType roleName : roleNames) {
            authorities.add(new SimpleGrantedAuthority(roleName.toString()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

  /*  List<AccountType> roles = Arrays.asList(user.getAccountType());
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();

        return userDetails;
    }*/
}
