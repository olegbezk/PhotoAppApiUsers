package com.photoapp.discovery.api.users.service;

import com.photoapp.discovery.api.users.data.UserEntity;
import com.photoapp.discovery.api.users.repository.UsersRepository;
import com.photoapp.discovery.api.users.shared.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto dto) {

        dto.setUserId(UUID.randomUUID().toString());
        dto.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);

        return modelMapper.map(usersRepository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity byEmail = usersRepository.findByEmail(email);

        if (byEmail == null) {
            throw new UsernameNotFoundException(email);
        }
        return modelMapper.map(byEmail, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity byEmail = usersRepository.findByEmail(username);

        if (byEmail == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(byEmail.getEmail(), byEmail.getEncryptedPassword(), true, true, true, true, Collections.emptyList());
    }
}
