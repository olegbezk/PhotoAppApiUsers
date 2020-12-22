package com.photoapp.discovery.api.users.service;

import com.photoapp.discovery.api.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto dto);
    UserDto getUserDetailsByEmail(String email);
}
