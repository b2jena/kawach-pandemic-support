package com.stackroute.config;

import com.stackroute.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;


@Service
public class JWTTokenGeneratorImpl implements JWTTokenGenerator {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Override
    public String generateToken(User user) {
        String jwtToken;

        jwtToken = Jwts.builder().setSubject(user.getId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact();

        HashMap<String, String> jwtTokenMap = new HashMap<>();
        jwtTokenMap.put("token", jwtToken);
        jwtTokenMap.put("message", message);
        return jwtToken;
    }

}
