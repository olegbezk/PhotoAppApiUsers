package com.photoapp.discovery.api.users.config;

import com.photoapp.discovery.api.users.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationProperties properties;

    @Autowired
    public SecurityConfiguration(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users/**").hasIpAddress(properties.getIp());

        http.headers().frameOptions().sameOrigin(); //h2 console config
    }
}
