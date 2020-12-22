package com.photoapp.discovery.api.users.repository;

import com.photoapp.discovery.api.users.data.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
