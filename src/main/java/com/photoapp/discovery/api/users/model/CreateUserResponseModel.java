package com.photoapp.discovery.api.users.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
