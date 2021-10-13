package com.stackroute.config;

import java.util.HashMap;

import com.stackroute.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;


@Service
public class JWTTokenGeneratorImpl implements JWTTokenGenerator{
    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Override
    public String generateToken(User user) {
        String jwtToken;

        jwtToken= Jwts.builder().setSubject(user.getId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,secret).compact();

        HashMap<String,String> jwtTokenMap=new HashMap<>();
        jwtTokenMap.put("token",jwtToken);
        jwtTokenMap.put("message",message);
        return jwtToken;
    }

}
