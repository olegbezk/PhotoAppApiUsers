package com.photoapp.discovery.api.users.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "gateway")
public class ApplicationProperties {
    private String ip;
}
