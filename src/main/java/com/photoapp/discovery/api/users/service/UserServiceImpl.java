package com.photoapp.discovery.api.users.service;

import com.photoapp.discovery.api.users.data.UserEntity;
import com.photoapp.discovery.api.users.repository.UsersRepository;
import com.photoapp.discovery.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto dto) {

        dto.setUserId(UUID.randomUUID().toString());

        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);

        userEntity.setEncryptedPassword(UUID.randomUUID().toString());

        return modelMapper.map(usersRepository.save(userEntity), UserDto.class);
    }
}
