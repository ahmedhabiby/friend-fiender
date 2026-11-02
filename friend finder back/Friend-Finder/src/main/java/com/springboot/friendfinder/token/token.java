package com.springboot.friendfinder.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Setter
@Getter
@ConfigurationProperties("token")
public class token {
    private String secret;
    private Duration duration;
}
