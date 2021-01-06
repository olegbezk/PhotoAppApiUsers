package com.photoapp.discovery.api.users.ui.controller;

import com.photoapp.discovery.api.users.model.CreateUserRequestModel;
import com.photoapp.discovery.api.users.model.CreateUserResponseModel;
import com.photoapp.discovery.api.users.service.UserService;
import com.photoapp.discovery.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RefreshScope
public class UsersController {

    private final Environment environment;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(
            final Environment environment,
            final UserService userService,
            final ModelMapper modelMapper
    ) {
        this.environment = environment;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + environment.getProperty("local.server.port") +
                ", with token: " + environment.getProperty("token.secret");
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel user) {

        UserDto userDto = modelMapper.map(user, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponseModel response = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
