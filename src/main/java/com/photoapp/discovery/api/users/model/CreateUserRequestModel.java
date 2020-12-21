package com.photoapp.discovery.api.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestModel {

    @NotNull(message = "First name can't be null")
    @Size(min = 2, message = "First name can't be less than two characters")
    private String firstName;

    @NotNull(message = "Last name can't be null")
    @Size(min = 2, message = "Last name can't be less than two characters")
    private String lastName;

    @NotNull(message = "Password can't be null")
    @Size(min = 8, max = 16, message = "Password should be more or equal that 8 characters and less than 16 characters")
    private String password;

    @NotNull(message = "Email can't be null")
    @Email
    private String email;
}
