package com.springboot.friendfinder.jwtAuth;

import com.springboot.friendfinder.token.token;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Configuration
public class HandleToken {
    private token token;
    private UserService userService;
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;
    private Duration duration;
    HandleToken(token token, UserService userService) {
        this.token = token;
        this.userService = userService;
        Key key = Keys.hmacShaKeyFor(token.getSecret().getBytes());
        jwtBuilder= Jwts.builder().signWith(key);
        jwtParser = Jwts.parser().setSigningKey(key);
        duration=token.getDuration();
    }

    public String createToken(usersDto usersDto) {
        return jwtBuilder
                .setSubject(usersDto.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(duration)))
                .claim("roles", usersDto.getRoles().stream().map(role -> role.getRoleName()).toList())
                .compact();

    }
    public usersDto validateToken(String token) {
        if (!jwtParser.isSigned(token)) {
            return null;
        }
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        String subject = claims.getSubject();
        usersDto usersDto = userService.findUserByUsername(subject);
        boolean isValid= Objects.nonNull(usersDto)&&expiration.after(new Date())&&issuedAt.before(expiration);

        if (isValid) {
            return usersDto;
        }
        return null;
    }
}
