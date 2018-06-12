package com.spsvn.authorisation.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by npkhanh on 6/12/2018.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("khanhn", "$2a$06$d8F2VsBciQ8DnXS4Yi0F3uCndclmK7Duua4dccYmhGygI0DOgluPa", new HashSet<>());
    }
}
