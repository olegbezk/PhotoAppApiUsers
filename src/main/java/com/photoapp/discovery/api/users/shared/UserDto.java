package com.photoapp.discovery.api.users.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = 5508253672890271835L;

    private String userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String encryptedPassword;
}
