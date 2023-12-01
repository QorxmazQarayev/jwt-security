package com.changesite.springbootsecurityjwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "aydanaydanaydanaydanaydanaydanaydanaydanaydanaydanaydanaydanayda";

    public String findUsername(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public <T> T exportToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }

    private Key getKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControle(String token, UserDetails userDetails) {
        final String username = findUsername(token);
        return (username.equals(userDetails.getUsername()) && !exportToken(token, Claims::getExpiration).before
                (new Date(System.currentTimeMillis())));
    }

    public String generateToken(UserDetails user) {
        return Jwts.builder().setClaims(new HashMap<>())
                .setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }
}
