package com.photoapp.discovery.api.users.service;

import com.photoapp.discovery.api.users.data.UserEntity;
import com.photoapp.discovery.api.users.repository.UsersRepository;
import com.photoapp.discovery.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            final UsersRepository usersRepository,
            final ModelMapper modelMapper,
            final BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto dto) {

        dto.setUserId(UUID.randomUUID().toString());
        dto.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);

        return modelMapper.map(usersRepository.save(userEntity), UserDto.class);
    }
}
