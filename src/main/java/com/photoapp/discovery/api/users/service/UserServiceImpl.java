package com.photoapp.discovery.api.users.service;

import com.photoapp.discovery.api.users.data.UserEntity;
import com.photoapp.discovery.api.users.repository.UsersRepository;
import com.photoapp.discovery.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto createUser(UserDto dto) {

        dto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = mapper.map(dto, UserEntity.class);

        userEntity.setEncryptedPassword(UUID.randomUUID().toString());

        usersRepository.save(userEntity);

        return null;
    }
}
